package com.example.service;

import java.time.LocalDateTime;
import java.util.List;

import org.quartz.CronExpression;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.model.ScheduleJob;


@Service
public class ScheduleUtilService {
	
	private Logger log=LoggerFactory.getLogger(getClass());
	
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
	

	public void addSchedulerJob(ScheduleJob scheduleJob) throws SchedulerException, Exception{
		scheduler.scheduleJob(scheduleJob.getJobDetail(), scheduleJob.getTrigger());
	}
	

	public void addSchedulerJobs(List<ScheduleJob> scheduleJobs){
		scheduleJobs.forEach(scheduleJob->{
			try {
				scheduler.scheduleJob(scheduleJob.getJobDetail(), scheduleJob.getTrigger());
			} catch (SchedulerException e) {
				scheduleLog(scheduleJob);
				e.printStackTrace();
			} catch (Exception e) {
				scheduleLog(scheduleJob);
				e.printStackTrace();
			}
		});
	}
	

	public boolean delScheduJob(ScheduleJob scheduleJob) throws SchedulerException{
	    return scheduler.deleteJob(scheduleJob.getJobKey());
	} 
	

	public void updateScheduJob(ScheduleJob scheduleJob) throws SchedulerException{
		scheduler.rescheduleJob(scheduleJob.getTriggerKey(), scheduleJob.getTrigger());

	}
	

	public void pauseScheduJob(ScheduleJob scheduleJob) throws SchedulerException{
		scheduler.pauseJob(scheduleJob.getJobKey());
	} 
	

	public void pauseScheduJobs(List<ScheduleJob> scheduleJobs){
		scheduleJobs.forEach(scheduleJob->{
			try {
				scheduler.pauseJob(scheduleJob.getJobKey());
			} catch (SchedulerException e) {
				scheduleLog(scheduleJob);
				e.printStackTrace();
			}
		});
	} 
	

	public void resumeScheduJob(ScheduleJob scheduleJob) throws SchedulerException{
		scheduler.resumeJob(scheduleJob.getJobKey());
	} 
	

	public void resumeScheduJobs(List<ScheduleJob> scheduleJobs){
		scheduleJobs.forEach(scheduleJob->{
			try {
				scheduler.resumeJob(scheduleJob.getJobKey());
			} catch (SchedulerException e) {
				scheduleLog(scheduleJob);
				e.printStackTrace();
			}
		});
	} 
	

	public void triggerScheduJob(ScheduleJob scheduleJob) throws SchedulerException{
	    scheduler.triggerJob(scheduleJob.getJobKey());
	} 
	
	private void scheduleLog(ScheduleJob scheduleJob){
		log.error("[{}],定时任务[{}]启动失败!",LocalDateTime.now(),scheduleJob.getJobKey());
	}
}
