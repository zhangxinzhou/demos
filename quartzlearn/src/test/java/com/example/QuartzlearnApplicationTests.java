package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = QuartzlearnApplication.class)
@WebAppConfiguration
public class QuartzlearnApplicationTests {

	@Autowired
	SchedulerFactory sf;
	
	@Test
	public void contextLoads() throws SchedulerException {
		sf.getAllSchedulers().forEach(System.out::println);
	}

}
