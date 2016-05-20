package com.bd.myapp.data;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public interface DBMSConnectionConfiguration {
	public DriverManagerDataSource dataSource();

}
