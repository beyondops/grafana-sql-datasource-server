package com.beyondops.sqlquery.model.datasource;

import lombok.Data;

/**
 * Created by caiqinzhou@gmail.com on 2019/1/17.
 */
@Data
public class DataSourceConfig {

    private String name;
    private String type;
    private String url;
    private String username;
    private String password;
    private String driver;
}
