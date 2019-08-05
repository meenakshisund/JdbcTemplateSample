package com.iamvickyav.jdbctemplatesample.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class DataUtil {

    @Bean
    JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(getH2DataSource());
    }

    private DataSource getH2DataSource() {
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                /*.addScript("classpath:schema.sql")
                .addScript("classpath:data.sql")*/
                .build();
        return dataSource;
    }
}
