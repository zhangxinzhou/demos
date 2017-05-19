package com.example.test;

import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.concurrent.DefaultManagedTaskScheduler;
import org.springframework.scheduling.support.CronSequenceGenerator;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.scheduling.support.PeriodicTrigger;

import com.example.schedujob.TestJob;

public class Test {

	 
	public static void main(String[] args) throws NoSuchMethodException {
		String cronStr="0/1 * * * * ?";
		CronSequenceGenerator.isValidExpression(cronStr);
		Trigger trigger=new CronTrigger(cronStr);
		Trigger trigge=new PeriodicTrigger(1000,TimeUnit.MICROSECONDS);
		ConcurrentTaskScheduler Scheduler =new DefaultManagedTaskScheduler();
		Scheduler.scheduleWithFixedDelay(new TestJob(), 100);
		Scheduler.schedule(new TestJob(), trigger);
		Scheduler.schedule(new TestJob(), trigge);
	}
}
