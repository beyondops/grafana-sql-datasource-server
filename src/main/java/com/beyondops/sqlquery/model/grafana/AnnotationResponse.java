package com.beyondops.sqlquery.model.grafana;

import lombok.Data;

/**
 * Created by caiqinzhou@gmail.com on 2019/1/19.
 */
@Data
public class AnnotationResponse {

    private Annotation annotation;
    private long time;
    private String title;
    private String tags;
    private String text;
}
