package com.github.hotire.springbatch.mybatis;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("database")
@Configuration
public class BatchDatabaseConfig {
    /**
     *
     * @see DataSource
     * @see com.zaxxer.hikari.HikariDataSource
     */
    @Primary
    @Bean(name = "batchDatasource")
    @ConfigurationProperties(prefix = "spring.batch.datasource")
    public DataSource ladmDataSource() {
        return DataSourceBuilder.create().build();
    }
}
