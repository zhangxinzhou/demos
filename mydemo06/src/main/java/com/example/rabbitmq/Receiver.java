package com.example.rabbitmq;

import java.time.LocalDateTime;

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
		System.out.println("Receiver 接收到消息的时间 : " + LocalDateTime.now());
		System.out.println("Receiver 消息内容: " + hello);
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
	
	
	//rabbitmq会将任务平均分配给每个消费者
	@RabbitHandler
	@RabbitListener(queues="test")
	public void doSendTest1(Integer count){	
		System.out.println("消费者1号接收到信息 : "+count);
	}
	
	@RabbitHandler
	@RabbitListener(queues="test")
	public void doSendTest2(Integer count){	
		System.out.println("消费者2号接收到信息 : "+count);
	}
}
