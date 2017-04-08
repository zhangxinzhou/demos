package com.example;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Student;
import com.example.service.StudentService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceTest {
	
	Logger log=LoggerFactory.getLogger(getClass());

	@Autowired
	StudentService stuService;
	
	@Test
	public void test(){
		Student test=new Student();
		test.setName("测试");
		test.setSex("男");
		test.setMsg("stuService测试");
		stuService.addStu(test);
		log.info("{}",stuService.findAllStu());
		Student stu=stuService.getStuById(1L);
		log.info("{}",stu);
	}
}
