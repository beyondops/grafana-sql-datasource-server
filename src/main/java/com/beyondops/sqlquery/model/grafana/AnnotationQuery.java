package com.beyondops.sqlquery.model.grafana;

import lombok.Data;

/**
 * Created by caiqinzhou@gmail.com on 2019/1/19.
 */
@Data
public class AnnotationQuery {

    private Annotation annotation;
    private DateRange range;
}
