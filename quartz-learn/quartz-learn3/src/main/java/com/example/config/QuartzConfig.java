package com.example.config;

import javax.sql.DataSource;

import org.quartz.Scheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class QuartzConfig {

	@Bean
	public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource){
		SchedulerFactoryBean schedulerFactoryBean=new SchedulerFactoryBean();
		schedulerFactoryBean.setDataSource(dataSource);
		return schedulerFactoryBean;
	}
	
	@Bean 
	public Scheduler scheduler(SchedulerFactoryBean schedulerFactoryBean){
		return schedulerFactoryBean.getScheduler();
	}
}
