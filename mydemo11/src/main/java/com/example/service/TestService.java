package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rabbitmq.Sender;

@Service
public class TestService {

	@Autowired
	Sender sender;
	
	public void Seckill(int num){
		for (int i = 0; i < num; i++) {
			sender.Seckill();
		}
	}
}
