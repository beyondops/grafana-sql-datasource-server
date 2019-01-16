package com.beyondops.sqlquery.model.grafana;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * Created by caiqinzhou@gmail.com on 2019/1/16.
 */

@Data
public class GrafanaQuery implements Serializable {

    private DateRange range;
    private int intervalMs;
    private List<Target> targets;
    private List<AdhocFilter> adhocFilters;
    private String format = "json";
    private int maxDataPoints = 0;

}
