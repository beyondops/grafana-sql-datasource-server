package com.beyondops.sqlquery.dao;

import com.beyondops.sqlquery.model.datasource.QueryResult;
import com.beyondops.sqlquery.model.grafana.Column;
import com.google.common.collect.Lists;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

/**
 * Created by caiqinzhou@gmail.com on 2019/1/17.
 */

@Component
@Slf4j
public class QueryDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public QueryResult query(String name, String sql) {

        QueryResult queryResult = new QueryResult();
        List<Column> columnList = Lists.newArrayList();
        List<List<Object>> result = Lists.newArrayList();
        jdbcTemplate.query(sql.toString(), new ResultSetExtractor<Integer>() {


            @Override
            public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {

                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    Column column = new Column();
                    column.setText(rsmd.getColumnName(i));
                    column.setType(rsmd.getColumnTypeName(i));
                    columnList.add(column);
                }
                int rowCount = 0;
                while (rs.next()) {
                    List<Object> row = Lists.newArrayList();
                    for (int i = 1; i <= columnCount; i++) {
                        row.add(rs.getObject(i));
                    }
                    result.add(row);
                    rowCount++;
                }

                return rowCount;
            }
        });
        log.debug("Column size: {}", columnList.size());
        log.debug("Result size: {}", result.size());
        queryResult.setColumns(columnList);
        queryResult.setResult(result);
        return queryResult;
    }
}
