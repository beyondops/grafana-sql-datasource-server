package com.beyondops.sqlquery.datasource;

import com.beyondops.sqlquery.config.SqlQuerySetting;
import com.beyondops.sqlquery.model.datasource.DataSourceConfig;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by caiqinzhou@gmail.com on 2019/1/17.
 */
@Configuration
@Slf4j
public class MultipleDataSourceConfiguration {


    @Bean
    public DataSource multipleDatasource(SqlQuerySetting sqlQuerySetting) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        if (null == sqlQuerySetting.getDatasources()
            || sqlQuerySetting.getDatasources().size() < 1) {
            return null;
        }
        for (DataSourceConfig dataSourceConfig : sqlQuerySetting.getDatasources()) {
            targetDataSources.put(dataSourceConfig.getName(), DataSourceBuilder
                .create()
                .username(dataSourceConfig.getUsername())
                .password(dataSourceConfig.getPassword())
                .url(dataSourceConfig.getUrl())
                .driverClassName(dataSourceConfig.getDriver())
                .build());
        }

        MultipleDataSourceRouter clientRoutingDatasource
            = new MultipleDataSourceRouter();
        clientRoutingDatasource.setTargetDataSources(targetDataSources);
        clientRoutingDatasource.setDefaultTargetDataSource(
            targetDataSources.get(sqlQuerySetting.getDatasources().get(0).getName()));
        return clientRoutingDatasource;
    }
}
