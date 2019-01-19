package com.beyondops.sqlquery.model.grafana;

import lombok.Data;

/**
 * Created by caiqinzhou@gmail.com on 2019/1/19.
 */
@Data
public class Annotation {

    private String name;
    private String datasource;
    private String iconColor;
    private String query;
    private boolean enable;
}
