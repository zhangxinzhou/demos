package com.example.config;


import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class JdbcConfig {
	
	private final String driverClassName="com.mysql.jdbc.Driver";
	private final String url="jdbc:mysql://127.0.0.1:3306/test?useSSL=false";
	private final String username="root";
	private final String password="root";
	

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource){
		return new JdbcTemplate(dataSource);
	}
	

	@Bean
	public DataSource dataSource(){
		DataSource dataSource=new DataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
	}
	
}
