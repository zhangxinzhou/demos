package com.example.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

	@Bean
	public Queue helloQueue(){
		return new Queue("hello");
	}
	
	@Bean
	public Queue SMSQueue(){
		return new Queue("sms");
	}
	
	@Bean
	public Queue EmailQueue(){
		return new Queue("email");
	}
	
	@Bean
	public Queue testQueue(){
		Queue queue=new Queue("test");
		
		return queue;
	}
}
