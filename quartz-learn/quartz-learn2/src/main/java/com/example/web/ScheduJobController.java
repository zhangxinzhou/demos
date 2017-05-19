package com.example.web;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.ScheduleJob;
import com.example.service.ScheduleJobService;



@Controller
public class ScheduJobController {
	
	@Autowired
	ScheduleJobService scheduleJobService;

	@GetMapping({"/","jobs"})
	public String jobs(ModelMap mm){
		mm.put("data", scheduleJobService.getAllScheduleJobs());
		return "jobs";
	}
	
	@RequestMapping("/startup")
	@ResponseBody
	public boolean startupScheduJob(ScheduleJob scheduleJob){
		try {
			scheduleJobService.startupScheduleJob(scheduleJob);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@RequestMapping("/shutdown")
	@ResponseBody
	public boolean shutdownScheduJob(ScheduleJob scheduleJob) throws SchedulerException{
		return scheduleJobService.shutdownScheduleJob(scheduleJob);
	}
	
	@RequestMapping("/updatecron")
	@ResponseBody
	public boolean updateScheduJobCron(ScheduleJob scheduleJob){
		try {
			scheduleJobService.updateScheduleJobCron(scheduleJob);
			return true;
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@RequestMapping("/isvalidcron")
	@ResponseBody
	public boolean shutdownScheduJob(String cronExpression){
		return scheduleJobService.isValidCronExpression(cronExpression);
	}
	
}
