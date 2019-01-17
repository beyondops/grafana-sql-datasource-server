package com.beyondops.sqlquery.service;

import com.beyondops.sqlquery.dao.QueryDao;
import com.beyondops.sqlquery.datasource.MultipleDataSourceHolder;
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

    public void query(String name, String sql) {

        MultipleDataSourceHolder.setRouteKey(name);
        queryDao.query(name, sql);
        MultipleDataSourceHolder.removeRouteKey();
    }
}
