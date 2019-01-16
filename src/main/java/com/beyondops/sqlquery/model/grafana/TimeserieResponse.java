package com.beyondops.sqlquery.model.grafana;

import java.util.List;
import lombok.Data;

/**
 * Created by caiqinzhou@gmail.com on 2019/1/16.
 */
@Data
public class TimeserieResponse {

    private String target;
    private List datapoints;
}
