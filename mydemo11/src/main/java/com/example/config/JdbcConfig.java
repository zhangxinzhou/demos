package com.example.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JdbcConfig {
	
	private static final String JDBC_DRIVER_CLASS_NAME="com.mysql.jdbc.Driver";
	private static final String JDBC_URL="jdbc:mysql://127.0.0.1:3306/test?useSSL=false";
	private static final String JDBC_USERNAME="root";
	private static final String JDBC_PASSWORD="root";

	@Bean
	public DataSource dataSource(){
		DataSource dataSource=new DataSource();
		dataSource.setDriverClassName(JDBC_DRIVER_CLASS_NAME);
		dataSource.setUrl(JDBC_URL);
		dataSource.setUsername(JDBC_USERNAME);
		dataSource.setPassword(JDBC_PASSWORD);
		return dataSource;
	}
}
