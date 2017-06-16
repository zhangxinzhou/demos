package com.example.schedule;

import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Profile(value="test")
@Component
public class MyTestSchedule {
	
	@Scheduled(cron="0/5 * * * * ?")
	public void doTest(){
		System.out.println("当前采用的配置是test配置");
	}
}
