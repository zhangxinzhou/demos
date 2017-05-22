package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.ScheduleBuilder;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.schedujob.MyScheduJobTest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DisallowConcurrentExecutionTest {

	@Autowired
	Scheduler scheduler;
	
	@Test
	public void test() throws Exception{
		scheduler.start();
		JobDetail jobDetail = JobBuilder.newJob(MyScheduJobTest.class).withIdentity(MyScheduJobTest.class.getSimpleName()).build();
		ScheduleBuilder<? extends Trigger> scheduleBuilder = SimpleScheduleBuilder.repeatSecondlyForTotalCount(100)
				                                                                  .withIntervalInSeconds(1);
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity(MyScheduJobTest.class.getSimpleName())
				                                     .startNow().withSchedule(scheduleBuilder)
				                                     .build();
		scheduler.scheduleJob(jobDetail, trigger);
		Thread.sleep(1000000);
		scheduler.isShutdown();
	}
}
