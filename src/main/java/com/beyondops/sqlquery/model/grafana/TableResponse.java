package com.beyondops.sqlquery.model.grafana;

import java.util.List;
import lombok.Data;

/**
 * Created by caiqinzhou@gmail.com on 2019/1/16.
 */

@Data
public class TableResponse {

    private List<Column> columns;
    private List rows;
    private final String type = "table";

}
