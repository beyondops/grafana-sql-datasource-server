package com.beyondops.sqlquery.service;

import com.beyondops.sqlquery.dao.QueryDao;
import com.beyondops.sqlquery.datasource.MultipleDataSourceHolder;
import com.beyondops.sqlquery.model.datasource.QueryResult;
import com.beyondops.sqlquery.model.grafana.AnnotationQuery;
import com.beyondops.sqlquery.model.grafana.AnnotationResponse;
import com.beyondops.sqlquery.model.grafana.QueryRequest;
import com.beyondops.sqlquery.model.grafana.SearchQuery;
import com.beyondops.sqlquery.model.grafana.SearchResponse;
import com.beyondops.sqlquery.model.grafana.TableResponse;
import com.beyondops.sqlquery.model.grafana.TimeserieResponse;
import com.google.common.collect.Lists;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by caiqinzhou@gmail.com on 2019/1/17.
 */
@Service
@Slf4j
public class QueryService {

    @Autowired
    QueryDao queryDao;

    public List<Object> query(final List<QueryRequest> queryRequests) {
        List<Object> result = Lists.newArrayList();
        for (QueryRequest queryRequest : queryRequests) {
            QueryResult queryResult = queryFromDB(queryRequest.getDatasource(),
                queryRequest.getRawSql());
            if ("table".equals(queryRequest.getType())) {
                result.add(queryResultToTableResponse(queryResult));
            } else {
                queryResultToTimeserieResponse(result, queryResult);
            }
        }
        return result;
    }

    public List<AnnotationResponse> annotation(AnnotationQuery annotationQuery) {
        List<AnnotationResponse> result = Lists.newArrayList();
        QueryResult queryResult = queryFromDB(annotationQuery.getAnnotation().getDatasource(),
            annotationQuery.getAnnotation().getQuery());
        if (!queryResult.checkAnnotationColumn()) {
            return result;
        }
        for (Map<String, Object> mapRow : queryResult.getMapResult()) {
            AnnotationResponse annotationResponse = new AnnotationResponse();
            annotationResponse.setAnnotation(annotationQuery.getAnnotation());
            annotationResponse.setText((String) mapRow.get("text"));
            annotationResponse.setTags((String) mapRow.get("tags"));
            annotationResponse.setTitle((String) mapRow.get("title"));
            annotationResponse.setTime((Long) mapRow.get("time"));
            result.add(annotationResponse);
        }
        return result;
    }

    public List<SearchResponse> search(SearchQuery searchQuery) {
        List<SearchResponse> result = Lists.newArrayList();
        QueryResult queryResult = queryFromDB(searchQuery.getDatasource(), searchQuery.getRawSql());

        if (queryResult.getColumns().size() == 1) {
            return queryResultToSearchResponseForOneColumn(queryResult);
        }
        if (queryResult.checkSearchResponseColumn()) {
            return queryResultToSearchResponseForTwoColumn(queryResult);
        }
        return result;
    }

    public QueryResult queryFromDB(String datasource, String query) {
        MultipleDataSourceHolder.setRouteKey(datasource);
        QueryResult queryResult = queryDao.query(query);
        MultipleDataSourceHolder.removeRouteKey();
        return queryResult;
    }

    public List<SearchResponse> queryResultToSearchResponseForOneColumn(QueryResult queryResult) {
        List<SearchResponse> result = Lists.newArrayList();
        for (List row : queryResult.getResult()) {
            SearchResponse searchResponse = new SearchResponse();
            String value = "" + row.get(0);
            searchResponse.setText(value);
            searchResponse.setValue(value);
            result.add(searchResponse);
        }
        return result;
    }

    public List<SearchResponse> queryResultToSearchResponseForTwoColumn(QueryResult queryResult) {
        List<SearchResponse> result = Lists.newArrayList();
        for (Map<String, Object> row : queryResult.getMapResult()) {
            SearchResponse searchResponse = new SearchResponse();
            searchResponse.setText(row.get("text").toString());
            searchResponse.setValue(row.get("value"));
            result.add(searchResponse);
        }
        return result;
    }

    /**
     * Map result to TimeserieResponse
     */
    public List<Object> queryResultToTimeserieResponse(List<Object> result,
        QueryResult queryResult) {
        if (!queryResult.checkQueryResponseColumn()) {
            return null;
        }
        Map<String, List<List<Object>>> series = new HashMap<>();
        for (Map<String, Object> row : queryResult.getMapResult()) {
            String target = (String) row.get("metric");
            if (!series.containsKey(target)) {
                List<List<Object>> datapoints = Lists.newArrayList();
                series.put(target, datapoints);
            }
            List<Object> datapoint = Lists.newArrayList();
            datapoint.add(row.get("value"));
            datapoint.add((long) row.get("time_sec") * 1000);
            series.get(target).add(datapoint);
        }
        for (String key : series.keySet()) {
            TimeserieResponse timeserieResponse = new TimeserieResponse();
            timeserieResponse.setTarget(key);
            timeserieResponse.setDatapoints(series.get(key));
            result.add(timeserieResponse);
        }
        return result;
    }

    /**
     * Map result to TableResponse
     */
    public TableResponse queryResultToTableResponse(QueryResult queryResult) {
        TableResponse tableResponse = new TableResponse();
        tableResponse.setColumns(queryResult.getColumns());
        tableResponse.setRows(queryResult.getResult());
        return tableResponse;
    }
}
