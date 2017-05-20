package com.example.service;

import java.util.List;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.model.ScheduleJob;
import com.example.repository.ScheduleJobRepository;

@Service
public class ScheduleJobService {

	@Autowired
	ScheduleJobRepository scheduleJobRepository;
	
	@Autowired
	ScheduleUtilService scheduleUtilService;
	
	public ScheduleJobRepository scheduleJobRepository(){
		return scheduleJobRepository;
	}
	
	public List<ScheduleJob> getAllScheduleJobs(){
		return scheduleJobRepository.findAll();
	}
	
	public List<ScheduleJob> getAllValidScheduleJobs(){
		ScheduleJob scheduleJob=new ScheduleJob();
		scheduleJob.setJobStatus("1");
		Example<ScheduleJob> example=Example.of(scheduleJob);
		return scheduleJobRepository.findAll(example);
	}
	
	
	/**
	 *  系统开启时,启动所有符合条件的定时任务
	 */
	public void startupValidScheduleJobs(){
		List<ScheduleJob> validScheduleJobs=getAllValidScheduleJobs();
		scheduleUtilService.addSchedulerJobs(validScheduleJobs);
	}
	
	/**
	 * 启动某个定时任务
	 * @param scheduleJob
	 * @throws SchedulerException
	 * @throws Exception
	 */
	public void startupScheduleJob(ScheduleJob scheduleJob) throws SchedulerException, Exception{
		scheduleJobRepository.save(scheduleJob);
		scheduleUtilService.addSchedulerJob(scheduleJob);
	}
	
	/**
	 * 关闭某个定时任务
	 * @param scheduleJob
	 * @return
	 * @throws SchedulerException
	 */
	public boolean shutdownScheduleJob(ScheduleJob scheduleJob) throws SchedulerException{
		scheduleJobRepository.save(scheduleJob);
		return scheduleUtilService.delScheduJob(scheduleJob);
	}
	
	/**
	 *                                 暂定和开启状态之间的切换
	 * @param jobId
	 * @throws SchedulerException
	 * @throws Exception
	 */
	public void changeStatus(Long jobId) throws SchedulerException, Exception{
		ScheduleJob scheduleJob=scheduleJobRepository.getOne(jobId);
		String original_status=scheduleJob.getJobStatus();
		String change_status="1".equals(original_status)?"0":"1";
		scheduleJob.setJobStatus(change_status);
		scheduleJobRepository.save(scheduleJob);
		if("1".equals(original_status)){//原状态是开启的,现在关闭
			scheduleUtilService.delScheduJob(scheduleJob);
		}else{//原状态是关闭的,现在开启
			scheduleUtilService.addSchedulerJob(scheduleJob);
		}
	}
		
	/**
	 * 更新定时任务的表达式
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void updateScheduleJobCron(Long jobId,String cronExpression) throws SchedulerException{
		ScheduleJob scheduleJob=scheduleJobRepository.findOne(jobId);
		scheduleJob.setCronExpression(cronExpression);
		scheduleJobRepository.save(scheduleJob);
		scheduleUtilService.updateScheduJob(scheduleJob);
	}
	
	
	public boolean isValidCronExpression(String cronExpression){
		return scheduleUtilService.isValidCron(cronExpression);
	}
	
}
