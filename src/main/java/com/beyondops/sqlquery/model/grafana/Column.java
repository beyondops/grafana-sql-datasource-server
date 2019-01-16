package com.beyondops.sqlquery.model.grafana;

import lombok.Data;

/**
 * Created by caiqinzhou@gmail.com on 2019/1/16.
 */
@Data
public class Column {

    private String text;
    private String type;

    public Column() {
    }

    public Column(String text, String type) {
        this.text = text;
        this.type = type;
    }
}
