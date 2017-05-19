package com.example.applicationevent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.example.service.ScheduleJobService;

@Component
public class MyContextRefreshedEvent implements ApplicationListener<ContextRefreshedEvent>{

	private static boolean flag=true;	

	@Autowired
	ScheduleJobService scheduleJobService;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		ScheduTaskStartup(flag);
	}
	
	/**
	 *                 启动定时任务
	 * @param flag     保证只执行一次
	 */
	private void ScheduTaskStartup(boolean flag){
		flag=false;
		scheduleJobService.startupValidScheduleJobs();
	}

}
