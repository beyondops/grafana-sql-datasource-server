package com.beyondops.sqlquery.model.grafana;

import lombok.Data;

/**
 * Created by caiqinzhou@gmail.com on 2019/1/18.
 */
@Data
public class TargetConfig {

    private String datasource;
    private String rawSql;
    private String target;

}
