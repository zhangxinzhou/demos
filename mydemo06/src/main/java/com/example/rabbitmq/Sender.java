package com.example.rabbitmq;

import java.time.LocalDateTime;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//消息生产者
@Component
public class Sender {

	private int count=0;
	
	@Autowired
	AmqpTemplate rabbitTemplate;
	
	public void send(){
		String context="现在时间 : "+LocalDateTime.now();
		System.out.println("Sender : "+context);
		rabbitTemplate.convertAndSend("hello",context);
	}
	
	public void sendSMS(){
		rabbitTemplate.convertAndSend("sms","发动短信");
	}
	
	public void sendEmail(){
		rabbitTemplate.convertAndSend("email","发送email");
	}
	
	public void sendTest(){
		count++;
		rabbitTemplate.convertAndSend("test",count);	
	}
}
