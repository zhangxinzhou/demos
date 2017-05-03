package com.example.rabbitmq;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.model.User;

//消息生产者
@Component
public class Sender {

	private static final SimpleDateFormat sdf=new SimpleDateFormat("HH时mm分");
	private static final Random rand = new Random();
	private static int count=0;
	
	@Autowired
	AmqpTemplate rabbitTemplate;
	
	public void send(){
		String context="现在时间 : "+LocalDateTime.now();
		System.out.println("Sender : "+context);
		rabbitTemplate.convertAndSend("hello",context);
	}
	
	public void savetest(){
		User u=new User();
		u.setName(sdf.format(new Date()));
		u.setAge(rand.nextInt(100));
		rabbitTemplate.convertAndSend("savetest",u);
	}
	
	public void Seckill(){
		count++;
		rabbitTemplate.convertAndSend("seckill",count);
	}
	
}
