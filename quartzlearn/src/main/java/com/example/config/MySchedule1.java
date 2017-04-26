package com.example.config;


import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MySchedule1{
	
	private Logger log=LoggerFactory.getLogger(getClass());
	
	@Scheduled(fixedRate = 60000)
	public void reportCurrentTime(){
		log.info("MySchedule1->当前时间 : [{}]",LocalDateTime.now());
	}	

}
