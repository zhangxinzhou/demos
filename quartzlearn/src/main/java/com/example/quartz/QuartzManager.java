package com.example.quartz;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;

public class QuartzManager {

	
	 private static String JOB_GROUP_NAME = "EXTJWEB_JOBGROUP_NAME";
	    private static String TRIGGER_GROUP_NAME = "EXTJWEB_TRIGGERGROUP_NAME";

	    /**
	     * @Description: 添加一个定时任务，使用默认的任务组名，触发器名，触发器组名
	     * 
	     * @param sched
	     *            调度器
	     * 
	     * @param jobName
	     *            任务名
	     * @param cls
	     *            任务
	     * @param time
	     *            时间设置，参考quartz说明文档
	     * 
	     * @Title: QuartzManager.java
	     */
	    public static void addJob(Scheduler sched, String jobName, @SuppressWarnings("rawtypes") Class cls, String time) {
	        try {
	            JobDetail jobDetail =JobBuilder.newJob(cls).withIdentity(jobName,JOB_GROUP_NAME).build();// 任务名，任务组，任务执行类 
	            // 触发器
	            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(time); 
	            CronTrigger trigger =TriggerBuilder.newTrigger().withIdentity(jobName, TRIGGER_GROUP_NAME).withSchedule(scheduleBuilder).build();// 触发器名,触发器组
	            sched.scheduleJob(jobDetail, trigger);
	            // 启动
	            if (!sched.isShutdown()) {
	                sched.start();
	            }
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }

	    /**
	     * @Description: 添加一个定时任务
	     * 
	     * @param sched
	     *            调度器
	     * 
	     * @param jobName
	     *            任务名
	     * @param jobGroupName
	     *            任务组名
	     * @param triggerName
	     *            触发器名
	     * @param triggerGroupName
	     *            触发器组名
	     * @param jobClass
	     *            任务
	     * @param time
	     *            时间设置，参考quartz说明文档
	     * 
	     * @Title: QuartzManager.java
	     */
	    public static void addJob(Scheduler sched, String jobName, String jobGroupName, String triggerName, String triggerGroupName, @SuppressWarnings("rawtypes") Class jobClass, String time) {
	        try {
	            JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName,JOB_GROUP_NAME).build();// 任务名，任务组，任务执行类
	            // 触发器
	            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(time); 
	            CronTrigger trigger =TriggerBuilder.newTrigger().withIdentity(jobName, TRIGGER_GROUP_NAME).withSchedule(scheduleBuilder).build();// 触发器名,触发器组
	            sched.scheduleJob(jobDetail, trigger);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }

	    /**
	     * @Description: 修改一个任务的触发时间(使用默认的任务组名，触发器名，触发器组名)
	     * 
	     * @param sched
	     *            调度器
	     * @param jobName
	     * @param time
	     * 
	     * @Title: QuartzManager.java
	     */
	    @SuppressWarnings("rawtypes")
	    public static void modifyJobTime(Scheduler sched, String jobName, String time) {
	        try {
	        	CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(time); 
	            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, TRIGGER_GROUP_NAME).withSchedule(scheduleBuilder).build();;
	            if (trigger == null) {
	                return;
	            }
	            String oldTime = trigger.getCronExpression();
	            JobKey jobKey=JobKey.jobKey(jobName);
	            if (!oldTime.equalsIgnoreCase(time)) {
	                JobDetail jobDetail = sched.getJobDetail(jobKey);
	                sched.getJobDetail(jobKey);
	                Class objJobClass = jobDetail.getJobClass();
	                removeJob(sched, jobName);
	                addJob(sched, jobName, objJobClass, time);
	            }
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }

	    /**
	     * @Description: 修改一个任务的触发时间
	     * 
	     * @param sched
	     *            调度器 *
	     * @param sched
	     *            调度器
	     * @param triggerName
	     * @param triggerGroupName
	     * @param time
	     * 
	     * @Title: QuartzManager.java
	     */
	    public static void modifyJobTime(Scheduler sched, String triggerName, String triggerGroupName, String time) {
	        try {
	        	TriggerKey triggerKey=TriggerKey.triggerKey(triggerName, triggerGroupName);
	            CronTrigger trigger = (CronTrigger) sched.getTrigger(triggerKey);	            
	            if (trigger == null) {
	                return;
	            }
	            String oldTime = trigger.getCronExpression();
	            if (!oldTime.equalsIgnoreCase(time)) {
	                CronTrigger ct = (CronTrigger) trigger;
	                // 修改时间
	                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(time); 
	                // 重启触发器
	                sched.resumeTrigger(triggerKey);
	            }
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }

	    /**
	     * @Description: 移除一个任务(使用默认的任务组名，触发器名，触发器组名)
	     * 
	     * @param sched
	     *            调度器
	     * @param jobName
	     * 
	     * @Title: QuartzManager.java
	     */
	    public static void removeJob(Scheduler sched, String jobName) {
	        try {
	        	TriggerKey triggerKey=TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME);
	        	JobKey jobKey=JobKey.jobKey(jobName, JOB_GROUP_NAME);
	            sched.pauseTrigger(triggerKey);// 停止触发器
	            sched.unscheduleJob(triggerKey);// 移除触发器
	            sched.deleteJob(jobKey);// 删除任务
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }

	    /**
	     * @Description: 移除一个任务
	     * 
	     * @param sched
	     *            调度器
	     * @param jobName
	     * @param jobGroupName
	     * @param triggerName
	     * @param triggerGroupName
	     * 
	     * @Title: QuartzManager.java
	     */
	    public static void removeJob(Scheduler sched, String jobName, String jobGroupName, String triggerName, String triggerGroupName) {
	        try {
	        	TriggerKey triggerKey=TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME);
	        	JobKey jobKey=JobKey.jobKey(jobName, JOB_GROUP_NAME);
	            sched.pauseTrigger(triggerKey);// 停止触发器
	            sched.unscheduleJob(triggerKey);// 移除触发器
	            sched.deleteJob(jobKey);// 删除任务
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }

	    /**
	     * @Description:启动所有定时任务
	     * 
	     * @param sched
	     *            调度器
	     * 
	     * @Title: QuartzManager.java
	     */
	    public static void startJobs(Scheduler sched) {
	        try {
	            sched.start();
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }

	    /**
	     * @Description:关闭所有定时任务
	     * 
	     * 
	     * @param sched
	     *            调度器
	     * 
	     * 
	     * @Title: QuartzManager.java
	     */
	    public static void shutdownJobs(Scheduler sched) {
	        try {
	            if (!sched.isShutdown()) {
	                sched.shutdown();
	            }
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }
}
