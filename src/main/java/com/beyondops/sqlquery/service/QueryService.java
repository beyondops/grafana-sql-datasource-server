package com.beyondops.sqlquery.service;

import com.beyondops.sqlquery.dao.QueryDao;
import com.beyondops.sqlquery.datasource.MultipleDataSourceHolder;
import com.beyondops.sqlquery.model.datasource.QueryResult;
import com.beyondops.sqlquery.model.grafana.GrafanaQuery;
import com.beyondops.sqlquery.model.grafana.TableResponse;
import com.beyondops.sqlquery.model.grafana.Target;
import com.beyondops.sqlquery.model.grafana.TimeserieResponse;
import com.google.common.collect.Lists;
import java.util.List;
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
            QueryResult queryResult = queryFromDB(grafanaQuery, target);
            if ("table".equals(target.getType())) {
                result.add(queryResultToTableResponse(target, queryResult));
            } else {
                result.add(queryResultToTimeserieResponse(target, queryResult));
            }
        }
        return result;
    }

    public QueryResult queryFromDB(GrafanaQuery grafanaQuery, Target target) {
        MultipleDataSourceHolder.setRouteKey(target.getTarget().getDatasource());
        QueryResult queryResult = queryDao.query(target.getTarget().getRawSql());
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
