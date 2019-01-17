package com.beyondops.sqlquery.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by caiqinzhou@gmail.com on 2019/1/17.
 */
public class MultipleDataSourceRouter extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return MultipleDataSourceHolder.getRouteKey();
    }
}
