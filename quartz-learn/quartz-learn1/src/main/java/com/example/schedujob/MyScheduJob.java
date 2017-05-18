package com.example.schedujob;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 首先注册为spring 管理的bean,否则不生效
 * @author Administrator
 *
 */
@Component
public class MyScheduJob {

	@Scheduled(fixedDelay=5000)
	public void Job1(){
		System.out.println("job1,当前时间:"+LocalDateTime.now());
	}
	
	@Scheduled(cron="0/1 * * * * ?")
	public void Job2(){
		System.out.println("job2,当前时间:"+LocalDateTime.now());
	}
}
