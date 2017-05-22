package com.example;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.CronScheduleBuilder;
import org.quartz.DailyTimeIntervalScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.ScheduleBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TimeOfDay;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.test.context.junit4.SpringRunner;

//import com.example.schedujob.MyScheduJob1;
import com.example.schedujob.MyScheduJobTest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuartzLearn3ApplicationTests {
	
	private final static String JOB_NAME="myjob";	
	private final static String JOB_GROUP_NAME = "myjobgroup";
	private final static String TRIGGER_NAME="mytrigger";
	private final static String TRIGGER_GROUP_NAME = "mytriggergroup";
	
	private final static String cronStr="0/1 * * * * ?";
	
	private final static JobKey jobKey=JobKey.jobKey(JOB_NAME, JOB_GROUP_NAME);
	private final static TriggerKey triggerKey = TriggerKey.triggerKey(TRIGGER_NAME,TRIGGER_GROUP_NAME);
	

	@Autowired
	SchedulerFactoryBean schedulerFactoryBean;
	
	@Autowired
	Scheduler scheduler;
	
	@Test
	public void contextLoads() throws SchedulerException, InterruptedException {
		scheduler.start();
		JobDetail jobDetail = JobBuilder.newJob(MyScheduJobTest.class).withIdentity(jobKey).build();
		ScheduleBuilder<? extends Trigger> scheduleBuilder = null;
		scheduleBuilder = CronScheduleBuilder.cronSchedule(cronStr);
		scheduleBuilder = DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule()
				                                          .startingDailyAt(TimeOfDay.hourAndMinuteAndSecondFromDate(new Date()))
				                                          .endingDailyAfterCount(10)
				                                          .withIntervalInSeconds(5);
		scheduleBuilder = SimpleScheduleBuilder.repeatSecondlyForTotalCount(100)
                                               .withIntervalInSeconds(1);
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).startNow().withSchedule(scheduleBuilder).build();
		scheduler.scheduleJob(jobDetail, trigger);
		Thread.sleep(10000);
		scheduler.isShutdown();
	}
	
    /** 
     * 从数据库中找到已经存在的job，并重新开户调度 
     */  
    public void resumeJob() {  
        try {  
   
            // ①获取调度器中所有的触发器组  
            List<String> triggerGroups = scheduler.getTriggerGroupNames();  
            // ②重新恢复在tgroup1组中，名为trigger1_1触发器的运行  
            for (int i = 0; i < triggerGroups.size(); i++) {  
                List<String> triggers = scheduler.getTriggerGroupNames();  
                for (int j = 0; j < triggers.size(); j++) {  
                    Trigger tg = scheduler.getTrigger(new TriggerKey(triggers  
                            .get(j), triggerGroups.get(i)));  
                    // ②-1:根据名称判断  
                    if (tg instanceof SimpleTrigger  
                            && tg.getDescription().equals("tgroup1.trigger1_1")) {  
                        // ②-1:恢复运行  
                        scheduler.resumeJob(new JobKey(triggers.get(j),  
                                triggerGroups.get(i)));  
                    }  
                }  
  
            }  
            scheduler.start();  
        } catch (Exception e) {  
            e.printStackTrace();   
        }  
    }

}
