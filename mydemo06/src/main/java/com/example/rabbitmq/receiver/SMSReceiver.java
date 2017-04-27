package com.example.rabbitmq.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.util.ServiceUtil;

//消息消费者
@Component
@RabbitListener(queues="sms")
public class SMSReceiver {

	
	
	@RabbitHandler
	public void process(String msg){
		System.out.println("接收到:"+msg);
		ServiceUtil.sendEmail();
	}
}
