package com.beyondops.sqlquery.service;

import com.beyondops.sqlquery.dao.QueryDao;
import com.beyondops.sqlquery.datasource.MultipleDataSourceHolder;
import com.beyondops.sqlquery.model.datasource.QueryResult;
import com.beyondops.sqlquery.model.grafana.AnnotationQuery;
import com.beyondops.sqlquery.model.grafana.AnnotationResponse;
import com.beyondops.sqlquery.model.grafana.GrafanaQuery;
import com.beyondops.sqlquery.model.grafana.TableResponse;
import com.beyondops.sqlquery.model.grafana.Target;
import com.beyondops.sqlquery.model.grafana.TimeserieResponse;
import com.google.common.collect.Lists;
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

    public List<Object> query(GrafanaQuery grafanaQuery) {
        List<Object> result = Lists.newArrayList();
        for (Target target : grafanaQuery.getTargets()) {
            QueryResult queryResult = queryFromDB(target.getTarget().getDatasource(),
                target.getTarget().getRawSql());
            if ("table".equals(target.getType())) {
                result.add(queryResultToTableResponse(target, queryResult));
            } else {
                result.add(queryResultToTimeserieResponse(target, queryResult));
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

    public QueryResult queryFromDB(String datasource, String query) {
        MultipleDataSourceHolder.setRouteKey(datasource);
        QueryResult queryResult = queryDao.query(query);
        MultipleDataSourceHolder.removeRouteKey();
        return queryResult;
    }

    /**
     * Map result to TimeserieResponse
     */
    public TimeserieResponse queryResultToTimeserieResponse(Target target,
        QueryResult queryResult) {
        TimeserieResponse timeserieResponse = new TimeserieResponse();
        timeserieResponse.setTarget(target.getTarget().getTarget());
        timeserieResponse.setDatapoints(queryResult.getResult());
        return timeserieResponse;
    }

    /**
     * Map result to TableResponse
     */
    public TableResponse queryResultToTableResponse(Target target, QueryResult queryResult) {
        TableResponse tableResponse = new TableResponse();
        tableResponse.setColumns(queryResult.getColumns());
        tableResponse.setRows(queryResult.getResult());
        return tableResponse;
    }
}
