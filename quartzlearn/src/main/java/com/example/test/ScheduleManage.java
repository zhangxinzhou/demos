package com.example.test;

import java.util.Date;

import org.quartz.CronExpression;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.quartz.ScheduleJob;

public class ScheduleManage {

	private static final Logger log=LoggerFactory.getLogger(ScheduleManage.class);
	
	//添加定时任务
	public static void add(Scheduler sched,ScheduleJob schedJob) throws SchedulerException{
		if(!isJob(schedJob)){
			return ;
		}
		JobKey jobKey=JobKey.jobKey(schedJob.getJobName(), schedJob.getJobGroup());//定时任务
		TriggerKey triggerKey=TriggerKey.triggerKey(schedJob.getJobName(), schedJob.getJobGroup());//触发器
		CronScheduleBuilder schedBuilder = CronScheduleBuilder.cronSchedule(schedJob.getCronExpression());//执行规则
		JobDetail job = JobBuilder.newJob(MyJob.class).withIdentity(jobKey).build();
		CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey)
                                             .withSchedule(schedBuilder)
                                             .build();
		Date date = sched.scheduleJob(job, trigger);
		if(sched.isShutdown()){
			sched.start();
		}		
		log.info("定时任务:[{}]在[{}]添加成功!",schedJob.getJobName(),date);
	}
	//删除定时任务
	public static void remove(Scheduler sched,ScheduleJob schedJob) throws SchedulerException{
		JobKey jobKey=JobKey.jobKey(schedJob.getJobName(), schedJob.getJobGroup());//定时任务
		TriggerKey triggerKey=TriggerKey.triggerKey(schedJob.getJobName(), schedJob.getJobGroup());//触发器
		sched.pauseTrigger(triggerKey);// 停止触发器
        sched.unscheduleJob(triggerKey);// 移除触发器
        sched.deleteJob(jobKey);// 删除任务
	}
	//修改定时任务
	
	//查询定时任务
	
	//开启某个定时任务
	//关闭某个定时任务
	//开启全部定时任务
	//关闭全部定时任务
	
	//验证cron表达式是否有效
	private static boolean isCron(String cronExpression){
		return cronExpression!=null?CronExpression.isValidExpression(cronExpression):false;
	}
	//判断ScheduleJob的beanClass是否实现了Job接口
	private static boolean isClass(String beanClass){
		if(beanClass==null){
			return false;
		}
		try {
			Class<?> cla=Class.forName(beanClass);			
			return Job.class.isAssignableFrom(cla);//是否实现了Job接口
		} catch (Exception e) {
			log.info("无效的class : [{}]",beanClass);
		}
		return false;
	}
	//判断是否是有效的Job
	private static boolean isJob(ScheduleJob schedJob){
		return schedJob!=null?(isCron(schedJob.getCronExpression())&&isClass(schedJob.getBeanClass())):false;
	}
	//测试
	public static void main(String[] args) {
		String cron="0 1 * * * ?";
		String beanclass="com.example.test.MyJob";
		System.out.println(isCron(cron));
		System.out.println(isClass(beanclass));
		ScheduleJob job=new ScheduleJob();
		job.setCronExpression(cron);
		job.setBeanClass(MyJob.class.getName());
		System.out.println(isJob(job));
	}
}
