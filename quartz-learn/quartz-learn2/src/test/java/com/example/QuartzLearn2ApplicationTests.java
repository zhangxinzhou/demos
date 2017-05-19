package com.example;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.SchedulerException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class QuartzLearn2ApplicationTests {
	
		
	//@Test
	public void contextLoads() {
		
	}

	
	@Test
	public void test() throws SchedulerException, InterruptedException{//定时任务无法在这里测试

	}
}
