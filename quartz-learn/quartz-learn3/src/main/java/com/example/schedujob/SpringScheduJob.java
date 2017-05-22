package com.example.schedujob;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Component;

@Component
public class SpringScheduJob {

	private final Logger log=LoggerFactory.getLogger(getClass());
	private static int COUNT=0;
	
	@Scheduled(cron="0 * * * * ?")
	public void reportTime1(){
		log.info("执行类 : [{}],方法 : [{}],时间 : [{}]",this.getClass().getName(),"reportTime1",LocalDateTime.now());
	}
	
	@Schedules(value={
			@Scheduled(cron="1 * * * * ?"),
			@Scheduled(cron="2 * * * * ?")
	})
	public void reportTime2(){
		log.info("执行类 : [{}],方法 : [{}],时间 : [{}]",this.getClass().getName(),"reportTime2",LocalDateTime.now());
		System.out.println(COUNT++);
	}
}
