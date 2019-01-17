package com.beyondops.sqlquery.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by caiqinzhou@gmail.com on 2019/1/18.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class QueryServiceTest {

    @Autowired
    QueryService queryService;

    @Test
    public void testQuery() {
        String name = "ds1";
        String sql = "SELECT * FROM ds1_user";
        queryService.query(name, sql);
        name = "ds2";
        sql = "SELECT * FROM ds2_user";
        queryService.query(name, sql);
    }
}
