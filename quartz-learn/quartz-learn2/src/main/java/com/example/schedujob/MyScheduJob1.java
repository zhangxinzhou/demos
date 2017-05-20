package com.example.schedujob;

import java.time.LocalDateTime;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyScheduJob1 implements Job{
	
	private final Logger log=LoggerFactory.getLogger(getClass());

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		log.info("任务 : [{}],触发器 : [{}],执行类 : [{}],时间 : [{}]",
				context.getJobDetail().getKey(),context.getTrigger().getKey(),this.getClass().getName(),LocalDateTime.now());
	}

}
