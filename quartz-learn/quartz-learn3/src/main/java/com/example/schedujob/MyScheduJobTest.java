package com.example.schedujob;



import java.time.LocalDateTime;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@PersistJobDataAfterExecution  //表示当正常执行完Job后, JobDataMap中的数据应该被改动, 以被下一次调用时用。(应配合@DisallowConcurrentExecution一起使用)
@DisallowConcurrentExecution  //加上这个之后,任务执行完毕后才会执行下一次(如果任务执行时间>任务的时间间隔 [Interval],间隔时间就不起作用)
public class MyScheduJobTest implements Job{
	
	private final Logger log=LoggerFactory.getLogger(getClass());

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		log.info("[{}]任务执行需要10秒,time : [{}]",this.getClass().getName(),LocalDateTime.now());
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
