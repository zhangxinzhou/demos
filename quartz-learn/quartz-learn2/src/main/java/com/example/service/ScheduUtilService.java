package com.example.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.CronExpression;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.ScheduleJob;

@Service
public class ScheduUtilService {

	@Autowired
	Scheduler scheduler;
	/**
	 * 启动所有定时任务
	 * @throws SchedulerException
	 */
	public void startScheduler() throws SchedulerException{
		scheduler.start();
	}
	/**
	 * 关闭所有定时任务
	 * @throws SchedulerException
	 */
	public void shutdownScheduler() throws SchedulerException{
		scheduler.shutdown();	
	}
	
	/**
	 *                cron表达式是否有效
	 * @param cron
	 * @return
	 */
	public boolean isValidCron(String cron){
		return CronExpression.isValidExpression(cron);
	}
	
	/**
	 *                        增加一个定时任务
	 * @param scheduleJob
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean addSchedulerJob(ScheduleJob scheduleJob){
		Class<?> jobClass=null;
		try {
			jobClass=Class.forName(scheduleJob.getJobClass());
			if(Job.class.isAssignableFrom(jobClass)){
				Class<? extends Job> temp=(Class<? extends Job>) jobClass;
				JobDetail jobDetail = JobBuilder.newJob(temp).withIdentity(scheduleJob.getJobName(), scheduleJob.getJobGroup()).build();
				CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression()); 
				Trigger trigger = TriggerBuilder.newTrigger().withIdentity(scheduleJob.getTriggerName(), scheduleJob.getTriggerGroup()).withSchedule(scheduleBuilder).build();
				scheduler.scheduleJob(jobDetail, trigger);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 *                           增加多个定时任务
	 * @param scheduleJobs
	 * @return
	 */
	public Map<String, Integer> addSchedulerJobs(List<ScheduleJob> scheduleJobs){
		Map<String, Integer> map=new HashMap<>();
		map.put("total", scheduleJobs.size());
		map.put("success", 0);
		scheduleJobs.forEach(scheduleJob->{
			if(addSchedulerJob(scheduleJob)){
				map.put("success", map.get("success")+1);
			}
		});
		return map;
	}
	
	/**
	 *                             删除某个定时任务
	 * @param scheduleJob
	 * @return
	 * @throws SchedulerException
	 */
	public boolean delScheduJob(ScheduleJob scheduleJob) throws SchedulerException{
		JobKey jobKey=JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
	    return scheduler.deleteJob(jobKey);
	} 
	
	/**
	 *                            更新某个定时任务
	 * @param scheduleJob
	 * @return
	 * @throws SchedulerException
	 */
	public void updateScheduJob(ScheduleJob scheduleJob) throws SchedulerException{
		TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getJobName(),scheduleJob.getJobGroup());
		//表达式调度构建器
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());
		//按新的cronExpression表达式重新构建trigger
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
		//按新的trigger重新设置job执行
		scheduler.rescheduleJob(triggerKey, trigger);

	}
	
	/**
	 *                                  暂停某个定时任务
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void pauseScheduJob(ScheduleJob scheduleJob) throws SchedulerException{
		JobKey jobKey=JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		scheduler.pauseJob(jobKey);

	} 
	
	/**
	 *                                 恢复暂停的定时任务
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void resumeScheduJob(ScheduleJob scheduleJob) throws SchedulerException{
		JobKey jobKey=JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
	    scheduler.resumeJob(jobKey);
	} 
	
	/**
	 *                                立即执行某个定时任务
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void triggerScheduJob(ScheduleJob scheduleJob) throws SchedulerException{
		JobKey jobKey=JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
	    scheduler.triggerJob(jobKey);
	} 
}
