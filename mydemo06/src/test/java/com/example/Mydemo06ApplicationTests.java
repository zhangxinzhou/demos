package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.rabbitmq.Sender;



@RunWith(SpringRunner.class)
@SpringBootTest
public class Mydemo06ApplicationTests {

	@Autowired
    Sender sender;
	
	
	@Test
	public void contextLoads() {
		sender.send();
	}

}
