package com.example.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
@EnableScheduling
public class SchedulerConfig {
	
/*	@Autowired
	ApplicationContext applicationContext;
	
	@Autowired
	DataSource dataSource;

	@Bean
	public SchedulerFactoryBean schedulerFactoryBean(){
		SchedulerFactoryBean sfb=new SchedulerFactoryBean(); 
		sfb.setApplicationContext(applicationContext);
		sfb.setDataSource(dataSource);
		return sfb;
	}*/
}
