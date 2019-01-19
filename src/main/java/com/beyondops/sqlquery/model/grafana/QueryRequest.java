package com.beyondops.sqlquery.model.grafana;

import lombok.Data;

/**
 * Created by caiqinzhou@gmail.com on 2019/1/19.
 */
@Data
public class QueryRequest {

    private String refId;
    private int intervalMs;
    private int maxDataPoints;
    private String datasource;
    private String rawSql;
    private String type;
    private String name;

}
