package com.beyondops.sqlquery.model.grafana;

import lombok.Data;

/**
 * Created by caiqinzhou@gmail.com on 2019/1/16.
 */
@Data
public class AdhocFilter {

    private String key;
    private String operator;
    private String value;

}
