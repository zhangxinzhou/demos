package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedis {

	@Autowired
	StringRedisTemplate stringRedisTemplate;

	
	@Test
	public void test(){
		String value=stringRedisTemplate.opsForValue().get("test");
		System.out.println(value);
	}
}
