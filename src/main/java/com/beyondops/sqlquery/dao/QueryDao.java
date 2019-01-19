package com.beyondops.sqlquery.dao;

import com.beyondops.sqlquery.model.datasource.QueryResult;
import com.beyondops.sqlquery.model.grafana.Column;
import com.google.common.collect.Lists;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public QueryResult query(String sql) {

        QueryResult queryResult = new QueryResult();
        List<Column> columnList = Lists.newArrayList();
        List<List<Object>> result = Lists.newArrayList();
        List<Map<String, Object>> mapResult = Lists.newArrayList();
        Map<String, Column> mapColumn = new HashMap<>();
        jdbcTemplate.query(sql.toString(), new ResultSetExtractor<Integer>() {


            @Override
            public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {

                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    Column column = new Column();
                    column.setText(rsmd.getColumnLabel(i));
                    column.setType(rsmd.getColumnTypeName(i));
                    columnList.add(column);
                    mapColumn.put(rsmd.getColumnLabel(i), column);
                }
                int rowCount = 0;
                while (rs.next()) {
                    List<Object> row = Lists.newArrayList();
                    Map<String, Object> mapRow = new HashMap<>();
                    for (int i = 1; i <= columnCount; i++) {
                        row.add(rs.getObject(i));
                    }
                    for (Column column : columnList) {
                        mapRow.put(column.getText(), rs.getObject(column.getText()));
                    }
                    result.add(row);
                    mapResult.add(mapRow);
                    rowCount++;
                }

                return rowCount;
            }
        });
        log.debug("Column size: {}", columnList.size());
        log.debug("Result size: {}", result.size());
        queryResult.setColumns(columnList);
        queryResult.setResult(result);
        queryResult.setMapResult(mapResult);
        queryResult.setMapColumn(mapColumn);
        return queryResult;
    }
}
