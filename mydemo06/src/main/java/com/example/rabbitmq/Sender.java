package com.example.rabbitmq;

import java.time.LocalDateTime;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//消息生产者
@Component
public class Sender {

	@Autowired
	AmqpTemplate rabbitTemplate;
	
	public void send(){
		String context="hello"+LocalDateTime.now();
		System.out.println(context);
		rabbitTemplate.convertAndSend(context);
	}
}
