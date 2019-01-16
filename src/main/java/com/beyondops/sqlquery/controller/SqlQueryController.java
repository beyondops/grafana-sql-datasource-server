package com.beyondops.sqlquery.controller;

import com.beyondops.sqlquery.model.grafana.Column;
import com.beyondops.sqlquery.model.grafana.GrafanaQuery;
import com.beyondops.sqlquery.model.grafana.TableResponse;
import com.beyondops.sqlquery.model.grafana.TimeserieResponse;
import com.google.common.collect.Lists;
import java.util.Date;
import java.util.List;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
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

    @GetMapping("/")
    public String[] index() {
        return new String[]{"Query data from SQL databases"};
    }


    @PostMapping("/query")
    public List search(@RequestBody final GrafanaQuery grafanaQuery) {
        List result = null;
        if (null == grafanaQuery.getTargets() || grafanaQuery.getTargets().size() < 1) {
            log.warn("Empty targets!");
            return result;
        }
        String type = "timeserie";
        // If one target is table, all response should be table format.
        if ("table".equals(grafanaQuery.getTargets().get(0).getType())) {
            type = "table";
        }
        if ("table".equals(type)) {
            result = mockTableResponse();
        } else {
            result = mockTimeserieResponse();
        }
        return result;
    }

    private List<TimeserieResponse> mockTimeserieResponse() {
        List<TimeserieResponse> result = Lists.newArrayList();
        long now = System.currentTimeMillis();
        for (int i = 0; i < 2; i++) {
            TimeserieResponse timeserieResponse = new TimeserieResponse();
            timeserieResponse.setTarget("target" + i);
            timeserieResponse.setDatapoints(Lists.newArrayList());
            result.add(timeserieResponse);
        }
        for (int j = 0; j < 20; j++) {
            for (TimeserieResponse timeserieResponse : result) {
                List<Object> point = Lists.newArrayList();
                point.add(new Random().nextFloat() * 100);
                point.add(now);
                timeserieResponse.getDatapoints().add(point);
            }
            now -= 3600 * 1000;
        }
        return result;
    }

    private List<TableResponse> mockTableResponse() {
        TableResponse tableResponse = new TableResponse();
        List<Column> columns = Lists.newArrayList();
        columns.add(new Column("Time", "time"));
        columns.add(new Column("Country", "string"));
        columns.add(new Column("Number", "number"));
        tableResponse.setColumns(columns);
        tableResponse.setRows(Lists.newArrayList());
        for (int i = 0; i < 20; i++) {
            List<Object> row = Lists.newArrayList();
            row.add(new Date());
            row.add("SE" + i);
            row.add(new Random().nextInt());
            tableResponse.getRows().add(row);
        }
        return Lists.newArrayList(tableResponse);
    }
}
