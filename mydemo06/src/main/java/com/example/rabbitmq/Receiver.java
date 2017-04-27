package com.example.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.util.ServiceUtil;

//消息消费者
@Component
public class Receiver {

	
	@RabbitHandler
	@RabbitListener(queues="hello")
	public void process(String hello){
		System.out.println("Receiver : " + hello);
	}
	
	@RabbitHandler
	@RabbitListener(queues="sms")
	public void doSendSMS(String msg){
		System.out.println("接收到信息 : "+msg);
		ServiceUtil.sendEmail();
	}
	
	@RabbitHandler
	@RabbitListener(queues="email")
	public void doSendEmail(String msg){
		System.out.println("接收到信息 : "+msg);
		ServiceUtil.sendEmail();
	}
}
