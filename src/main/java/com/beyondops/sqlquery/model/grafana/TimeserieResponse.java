package com.beyondops.sqlquery.model.grafana;

import com.google.common.collect.Lists;
import java.util.List;
import lombok.Data;

/**
 * Created by caiqinzhou@gmail.com on 2019/1/16.
 */
@Data
public class TimeserieResponse {

    private String target;
    private List datapoints;

    public TimeserieResponse() {
        this.target = "unknown";
        this.datapoints = Lists.newArrayList();
    }
}
