package com.beyondops.sqlquery.controller;

import com.beyondops.sqlquery.model.grafana.AnnotationQuery;
import com.beyondops.sqlquery.model.grafana.GrafanaQuery;
import com.beyondops.sqlquery.service.QueryService;
import com.google.common.collect.Lists;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by caiqinzhou@gmail.com on 2019/1/16.
 */
@RestController
@CrossOrigin
@Slf4j
public class SqlQueryController {

    @Autowired
    QueryService queryService;

    @GetMapping("/")
    public String[] index() {
        return new String[]{"Query data from SQL databases"};
    }


    @PostMapping("/query")
    public List query(@RequestBody final GrafanaQuery grafanaQuery) {
        List result = Lists.newArrayList();
        if (null == grafanaQuery.getTargets() || grafanaQuery.getTargets().size() < 1) {
            log.warn("Empty targets!");
            return result;
        }

        result = queryService.query(grafanaQuery);
        return result;
    }

    @PostMapping("/annotations")
    public List annotation(@RequestBody final AnnotationQuery annotationQuery) {
        List result = Lists.newArrayList();
        if (null == annotationQuery.getAnnotation() || Strings
            .isEmpty(annotationQuery.getAnnotation().getQuery())) {
            log.warn("Please input query!");
            return result;
        }
        result = queryService.annotation(annotationQuery);
        return result;
    }
}
