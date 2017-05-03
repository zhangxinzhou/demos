package com.example.rabbitmq;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.model.User;
import com.example.repository.UserRepository;

//消息消费者
@Component
public class Receiver {
	
	private List<User> users=new ArrayList<>(10);

	@Autowired
	UserRepository userRepository;
	
	@RabbitHandler
	@RabbitListener(queues="hello")
	public void process(String hello){
		System.out.println("Receiver 接收到消息的时间 : " + LocalDateTime.now());
		System.out.println("Receiver 消息内容: " + hello);
	}
	

	@RabbitHandler
	@RabbitListener(queues="savetest")
	public void savetest(User u){	//这种方式很蠢,如果服务器重启会丢失数据	
		users.add(u);
		if(users.size()>=10){
			userRepository.save(users);
			users.clear();
		}
		System.out.println(">>>>>>>当前user个数:"+userRepository.count());
	}
	
	@RabbitHandler
	@RabbitListener(queues="seckill")
	public void Seckill(int count){
		System.out.println(">>>>>>>>"+count);
	}
}
