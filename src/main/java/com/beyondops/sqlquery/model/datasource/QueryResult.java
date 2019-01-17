package com.beyondops.sqlquery.model.datasource;

import com.beyondops.sqlquery.model.grafana.Column;
import java.util.List;
import lombok.Data;

/**
 * Created by caiqinzhou@gmail.com on 2019/1/18.
 */
@Data
public class QueryResult {

    List<Column> columns;
    List<List<Object>> result;
}
