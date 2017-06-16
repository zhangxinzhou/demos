package com.example.schedule;

import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Profile(value="dev")
@Component
public class MyDevSchedule {

	
	@Scheduled(cron="0/5 * * * * ?")
	public void doDev(){
		System.out.println("当前采用的配置是dev配置");
	}
}
