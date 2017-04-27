package com.example.rabbitmq.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 发送短信的消息生产者
 * @author ZXZ
 *
 */
@Component
public class SMSSender {

	@Autowired
	AmqpTemplate rabbitTemplate;
	
	public void sendSMS(){
		rabbitTemplate.convertAndSend("sms","通知发送短信");
	}
}
