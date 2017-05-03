package com.example.schedu;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.rabbitmq.Sender;

@Component
public class MySchedu {
	
	@Autowired
	Sender sender;
	
	private Logger log=LoggerFactory.getLogger(getClass());

	@Scheduled(cron="0 0 0/1 * * ?")
	public void reportCurrentTIme(){
		log.info("现在时间 : [{}]",LocalDateTime.now());
	}
	
	
	@Scheduled(cron="0/10 * * * * ?")
	public void testRabbitMQ(){
		sender.savetest();
	}
	
}
