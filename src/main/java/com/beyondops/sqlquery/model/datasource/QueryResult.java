package com.beyondops.sqlquery.model.datasource;

import com.beyondops.sqlquery.model.grafana.Column;
import java.util.List;
import java.util.Map;
import lombok.Data;

/**
 * Created by caiqinzhou@gmail.com on 2019/1/18.
 */
@Data
public class QueryResult {

    List<Column> columns;
    Map<String, Column> mapColumn;
    List<List<Object>> result;
    List<Map<String, Object>> mapResult;

    private static final String[] queryResponseFields = {
        "time_sec",
        "value",
        "metric"
    };

    private static final String[] annotationFields = {
        "time",
        "text",
        "tags",
        "title"
    };
    private static final String[] searchResponseFields = {
        "text",
        "value"
    };


    public boolean checkAnnotationColumn() {
        for (String field : annotationFields) {
            if (!mapColumn.containsKey(field)) {
                return false;
            }
        }
        return true;
    }

    public boolean checkSearchResponseColumn() {
        for (String field : searchResponseFields) {
            if (!mapColumn.containsKey(field)) {
                return false;
            }
        }
        return true;
    }

    public boolean checkQueryResponseColumn() {
        for (String field : queryResponseFields) {
            if (!mapColumn.containsKey(field)) {
                return false;
            }
        }
        return true;
    }
}
