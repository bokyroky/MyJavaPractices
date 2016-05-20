package com.bd.myapp.data;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


@Configuration
public class SQLServerConnectionConfiguration implements DBMSConnectionConfiguration {
    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new org.springframework.jdbc.datasource.DriverManagerDataSource();
        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSource.setUsername("sa");
        dataSource.setPassword("SQL");
        dataSource.setUrl("jdbc:sqlserver://localhost;DataBaseName=Articles");
        return dataSource;
    }
    
    @Bean
    public JdbcTemplate jdbcTemplate() {    
        final JdbcTemplate jdbcTemplate = new JdbcTemplate();

        jdbcTemplate.setDataSource(dataSource()); 

        return jdbcTemplate;
    }
    

}
