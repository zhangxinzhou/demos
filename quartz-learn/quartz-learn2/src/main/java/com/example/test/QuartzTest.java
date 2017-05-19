package com.example.test;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

import com.example.schedujob.MyScheduJob1;
import com.example.schedujob.MyScheduJob2;

/**
 * 静态测试类
 * @author Administrator
 *
 */
public class QuartzTest {

	private final static String JOB_NAME="myjob";
	private final static String TRIGGER_NAME="mytrigger";
	private final static String JOB_GROUP_NAME = "myjobgroup";
	private final static String TRIGGER_GROUP_NAME = "mytriggergroup";
	
	private final static String cronStr1="0/1 * * * * ?";
	private final static String cronStr2="0/2 * * * * ?";
	
	private final static TriggerKey triggerKey = TriggerKey.triggerKey(JOB_GROUP_NAME,TRIGGER_GROUP_NAME);
	
	public static void main(String[] args) throws Exception {
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		scheduler.start();
		//simpleScheduTest();
		
		addScheduJob(scheduler,MyScheduJob2.class, JOB_NAME, JOB_GROUP_NAME, TRIGGER_NAME, TRIGGER_GROUP_NAME,cronStr1);
		addScheduJob(scheduler,MyScheduJob2.class, JOB_NAME, JOB_GROUP_NAME, TRIGGER_NAME, TRIGGER_GROUP_NAME,cronStr2);
	}
	
	/**
	 * 简单测试
	 * @throws Exception
	 */
	public static void simpleScheduTest() throws Exception{
		// 1、创建一个JobDetail实例，指定Quartz
	    JobDetail jobDetail = JobBuilder.newJob(MyScheduJob1.class).withIdentity(JOB_NAME, JOB_GROUP_NAME).build();
	    CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronStr1); 
	    // 2、创建Trigger
	    Trigger trigger = TriggerBuilder.newTrigger().withIdentity(TRIGGER_NAME, TRIGGER_GROUP_NAME).startNow().withSchedule(scheduleBuilder).build();
	    Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
	    scheduler.start();
	    scheduler.scheduleJob(jobDetail, trigger);
	}
	
	/**
	 *                      新增定时任务
	 * @param scheduler     scheduler
	 * @param job           做定时任务的实现类
	 * @param jobName       定时任务名称
	 * @param jobGroup      定时任务组名
	 * @param triggerName   定时任务触发器名称
	 * @param triggerGroup  定时任务触发器组别
	 * @param cronStr       定时任务cron表达式
	 * @throws Exception    
	 */
	public static void addScheduJob(Scheduler scheduler,Class<? extends Job> job,
			                     String jobName,String jobGroup,String triggerName,String triggerGroup,String cronStr) throws Exception{
		// 1、创建一个JobDetail实例，指定Quartz
	    JobDetail jobDetail = JobBuilder.newJob(job).withIdentity(jobName, jobGroup).build();
	    CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronStr); 
	    // 2、创建Trigger
	    Trigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerName, triggerGroup).withSchedule(scheduleBuilder).build();
	    if(!scheduler.isStarted()||scheduler.isShutdown()){//如果scheduler未开启,就把他开启
	    	scheduler.start();
	    }
	    scheduler.start();
	    scheduler.scheduleJob(jobDetail, trigger);
	}
	/**
	 *                    删除某个定时任务
	 * @param scheduler   
	 * @param jobName     定时任务名称
	 * @throws SchedulerException 
	 */
	public static void delScheduJob(Scheduler scheduler,String jobName,String triggerGroup) throws SchedulerException {
		JobKey jobKey = JobKey.jobKey(jobName,triggerGroup);
		scheduler.deleteJob(jobKey);
	}
	/**
	 *                    更新某个定时任务的cron表达式
	 * @param scheduler
	 * @param jobName
	 * @param triggerGroup
	 * @param cron
	 * @throws SchedulerException 
	 */
	public static void updateScheduJob(Scheduler scheduler,String jobName,String triggerGroup,String cron) throws SchedulerException {
		TriggerKey triggerKey = TriggerKey.triggerKey(jobName,triggerGroup);
		//表达式调度构建器
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
		//按新的cronExpression表达式重新构建trigger
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
		//按新的trigger重新设置job执行
		scheduler.rescheduleJob(triggerKey, trigger);
	}
	
	/**
	 *                                     暂停某个定时任务
	 * @param scheduler
	 * @param jobName
	 * @param triggerGroup
	 * @throws SchedulerException
	 */
	public static void pauseScheduJob(Scheduler scheduler,String jobName,String triggerGroup) throws SchedulerException{
		JobKey jobKey = JobKey.jobKey(jobName, triggerGroup);
		scheduler.pauseJob(jobKey);
	}
	
	/**
	 *                                    恢复某个定时任务
	 * @param scheduler
	 * @param jobName
	 * @param triggerGroup
	 * @throws SchedulerException
	 */
	public static void resumeScheduJob(Scheduler scheduler,String jobName,String triggerGroup) throws SchedulerException{
		JobKey jobKey = JobKey.jobKey(jobName, triggerGroup);
		scheduler.resumeJob(jobKey);
	}
	
	/**
	 *                                   立即运行某个任务
	 * @param scheduler
	 * @param jobName
	 * @param triggerGroup
	 * @throws SchedulerException
	 */
	public static void triggerScheduJob(Scheduler scheduler,String jobName,String triggerGroup) throws SchedulerException{
		JobKey jobKey = JobKey.jobKey(jobName, triggerGroup);
		scheduler.triggerJob(jobKey);
	}
	
	public static Trigger getDefaultTrigger(Scheduler scheduler) throws SchedulerException{
		Trigger trigger=scheduler.getTrigger(triggerKey);
		if(trigger==null){
			trigger=TriggerBuilder.newTrigger().withIdentity(triggerKey).build();
		}
		return trigger;
	}

}
