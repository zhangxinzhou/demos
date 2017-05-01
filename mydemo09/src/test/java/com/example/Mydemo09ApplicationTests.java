package com.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.model.User;
import com.example.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Mydemo09ApplicationTests {

	@Autowired
	UserRepository userRepository;

	@Autowired
	CacheManager cacheManager;

	@Before
	public void before() {
		User u=new User();
		u.setAge(10);
		u.setName("AAA");
		userRepository.save(u);
	}

	@Test
	public void test() throws Exception {

		User u1 = userRepository.findByName("AAA");
		System.out.println("第一次查询：" + u1.getAge());

		User u2 = userRepository.findByName("AAA");
		System.out.println("第二次查询：" + u2.getAge());

		u1.setAge(20);
		userRepository.save(u1);
		User u3 = userRepository.findByName("AAA");
		System.out.println("第三次查询：" + u3.getAge());

	}

}
