package com.example.applicationevent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

@Component
public class MyContextRefreshedEvent implements ApplicationListener<ContextRefreshedEvent>{

	private static boolean flag=true;
	
	@Autowired
	SchedulerFactoryBean sfb;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		if(flag){
			flag=false;
			System.out.println(sfb.hashCode());		
		}
	}

}
