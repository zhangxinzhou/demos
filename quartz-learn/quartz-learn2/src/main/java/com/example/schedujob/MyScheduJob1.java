package com.example.schedujob;

import java.time.LocalDateTime;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MyScheduJob1 implements Job{

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("类 : "+this.getClass().getSimpleName()+",报时 : "+LocalDateTime.now());
	}

}
