package com.beyondops.sqlquery.config;

import com.beyondops.sqlquery.model.datasource.DataSourceConfig;
import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by caiqinzhou@gmail.com on 2019/1/17.
 */
@Configuration
@ConfigurationProperties("sqlquery")
@Data
public class SqlQuerySetting {

    List<DataSourceConfig> datasources;
}
