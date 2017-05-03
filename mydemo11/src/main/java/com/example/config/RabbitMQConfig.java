package com.example.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbit MQ队列设置
 * @author Administrator
 *
 */
@Configuration
public class RabbitMQConfig {

	@Bean
	public Queue helloQueue(){
		return new Queue("hello");
	}
	
	@Bean
	public Queue SMSQueue(){
		return new Queue("savetest");
	}

	@Bean
	public Queue Seckill(){
		Queue queue= new Queue("seckill");
		return queue;
	}
}
