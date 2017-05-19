package com.example.test;


import org.quartz.CalendarIntervalScheduleBuilder;
import org.quartz.CronScheduleBuilder;
import org.quartz.DailyTimeIntervalScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.ScheduleBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.TimeOfDay;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.example.schedujob.MyScheduJob1;

public class TriggerTest {


	public static void main(String[] args) {
		startScheduler();
	}

	public static void startScheduler() {
		try {
			// 创建并启动调度实例
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			scheduler.start();
			System.out.println("Scheduler has been started");
			JobDetail jobDetail = JobBuilder.newJob(MyScheduJob1.class).withIdentity("test").build();
			/*
			 * 创建一个立即启动的触发器
			 */
			ScheduleBuilder<? extends Trigger> scheduleBuilder = null; 
			scheduleBuilder = CronScheduleBuilder.cronSchedule("0/1 * * * * ?");
			scheduleBuilder = CalendarIntervalScheduleBuilder.calendarIntervalSchedule().withIntervalInSeconds(1);
			scheduleBuilder = SimpleScheduleBuilder.repeatSecondlyForTotalCount(10, 1);
			scheduleBuilder = DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule()
					                                          .startingDailyAt(TimeOfDay.hourAndMinuteOfDay(9, 43))
					                                          .endingDailyAt(TimeOfDay.hourAndMinuteOfDay(9, 45))
					                                          .withIntervalInSeconds(1);			
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("test").startNow().withSchedule(scheduleBuilder).build();
			scheduler.scheduleJob(jobDetail, trigger);
		} catch (SchedulerException ex) {
			ex.printStackTrace();
		}
	}

}
