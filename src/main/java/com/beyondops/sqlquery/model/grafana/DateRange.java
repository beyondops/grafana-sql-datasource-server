package com.beyondops.sqlquery.model.grafana;

import java.util.Date;
import lombok.Data;

/**
 * Created by caiqinzhou@gmail.com on 2019/1/16.
 */
@Data
public class DateRange {
    private Date from;
    private Date to;
}
