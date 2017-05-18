package com.example.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

public class Test {

	public void go() throws Exception {
		// 首先，必需要取得一个Scheduler的引用
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		// jobs可以在scheduled的sched.start()方法前被调用
		// job 1将每隔20秒执行一次
		JobKey jobKey1=JobKey.jobKey("job1", "group1");//定时任务
		JobKey jobKey2=JobKey.jobKey("job2", "group1");
		TriggerKey triggerKey1=TriggerKey.triggerKey("trigger1", "group1");//触发器
		TriggerKey triggerKey2=TriggerKey.triggerKey("trigger2", "group1");
		CronScheduleBuilder schedBuilder1 = CronScheduleBuilder.cronSchedule("0 0/1 * * * ?");//执行规则
		CronScheduleBuilder schedBuilder2 = CronScheduleBuilder.cronSchedule("0/5 * * * * ?");
		JobDetail job = JobBuilder.newJob(MyJob.class).withIdentity(jobKey1).build();
		CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey1)
				                            .withSchedule(schedBuilder1)
				                            .build();
		Date ft = sched.scheduleJob(job, trigger);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
		System.out.println(job.getKey() + " 已被安排执行于: " + sdf.format(ft) + "，并且以如下重复规则重复执行: " + trigger.getCronExpression());

		// job 2将每2分钟执行一次（在该分钟的第15秒)
		job = JobBuilder.newJob(MyJob.class).withIdentity(jobKey2).build();
		trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey2)
				                             .withSchedule(schedBuilder2)
				                             .build();
		ft = sched.scheduleJob(job, trigger);
		System.out.println(job.getKey() + " 已被安排执行于: " + sdf.format(ft) + "，并且以如下重复规则重复执行: " + trigger.getCronExpression());
		
		
		// 开始执行，start()方法被调用后，计时器就开始工作，计时调度中允许放入N个Job
		sched.start();
		try {
			// 主线程等待一分钟
			Thread.sleep(60L * 1000L);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 关闭定时调度，定时器不再工作
		//sched.shutdown(true);
	}

	public static void main(String[] args) throws Exception {
		Test t = new Test();
		t.go();
	}
}
