package com.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.model.User;
import com.example.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Mydemo08ApplicationTests {

	@Autowired
	private UserRepository userRepository;
	@Before
	public void before() {
		User u=new User();
		u.setName("AAA");
		u.setAge(20);
		userRepository.save(u);
	}
	@Test
	public void test() throws Exception {
		User u1 = userRepository.findByName("AAA");
		System.out.println("第一次查询：" + u1.getAge());
		User u2 = userRepository.findByName("AAA");
		System.out.println("第二次查询：" + u2.getAge());
	}
}
