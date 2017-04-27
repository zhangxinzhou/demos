package com.example.rabbitmq.sender;


import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 发送邮件的消息生产者
 * @author ZXZ
 *
 */
@Component
public class EmailSender {

	@Autowired
	AmqpTemplate rabbitTemplate;
	
	
	public void sendEmail(){
		rabbitTemplate.convertAndSend("email","通知发送邮件");
	}
}
