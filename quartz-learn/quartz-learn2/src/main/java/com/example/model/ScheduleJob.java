package com.example.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;


@Entity
public class ScheduleJob implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	/** 任务id */
	@Id
	@GeneratedValue
    private Long jobId;
    /** 任务名称 */
	@Column(nullable=false)
    private String jobName;
    /** 任务分组 */
	@Column(nullable=false)
    private String jobGroup;  
    /** 触发器名称 */   
    private String triggerName;   
    /** 触发器组别 */
    private String triggerGroup;
    /** 任务状态 0禁用 1启用*/
    @Column(nullable=false)
    private String jobStatus;
    /** 任务运行时间表达式 */
    @Column(nullable=false)
    private String cronExpression;
    /** 任务描述 */
    private String jobDesc;
    /** 任务类(实现job接口)*/
    @Column(nullable=false)
    private String jobClass;
    /** 是否运行删除*/
    private boolean delable;
    
    
	public Long getJobId() {
		return jobId;
	}
	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getJobGroup() {
		return jobGroup;
	}
	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}
	public String getTriggerName() {
		return triggerName;
	}
	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}
	public String getTriggerGroup() {
		return triggerGroup;
	}
	public void setTriggerGroup(String triggerGroup) {
		this.triggerGroup = triggerGroup;
	}
	public String getJobStatus() {
		return jobStatus;
	}
	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}
	public String getCronExpression() {
		return cronExpression;
	}
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}
	public String getJobDesc() {
		return jobDesc;
	}
	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}
	public String getJobClass() {
		return jobClass;
	}
	public void setJobClass(String jobClass) {
		this.jobClass = jobClass;
	}	
    public boolean isDelable() {
		return delable;
	}
	public void setDelable(boolean delable) {
		this.delable = delable;
	}

	
	@Override
	public String toString() {
		return "ScheduleJob [jobId=" + jobId + ", jobName=" + jobName + ", jobGroup=" + jobGroup + ", triggerName="
				+ triggerName + ", triggerGroup=" + triggerGroup + ", jobStatus=" + jobStatus + ", cronExpression="
				+ cronExpression + ", jobDesc=" + jobDesc + ", jobClass=" + jobClass + ", delable=" + delable + "]";
	}
	
	public JobKey getJobKey(){
    	JobKey jobKey=JobKey.jobKey(this.jobName, this.jobGroup);
    	return jobKey;
    }
    
    public TriggerKey getTriggerKey(){
    	//TriggerKey triggerKey=TriggerKey.triggerKey(this.triggerName, this.triggerGroup);
    	TriggerKey triggerKey=TriggerKey.triggerKey(this.jobName, this.jobGroup);
    	return triggerKey;
    }

    @SuppressWarnings("unchecked")
	public JobDetail getJobDetail() throws Exception {
    	Class<? extends Job> jobClass=(Class<? extends Job>) Class.forName(this.jobClass);
    	JobDetail jobDetail = JobBuilder.newJob(jobClass)
    			                        .withIdentity(getJobKey())
    			                        .build();
    	return jobDetail;
    }
	
    public Trigger getTrigger(){
    	CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(this.cronExpression); 
    	Trigger trigger = TriggerBuilder.newTrigger()
    			                        .withIdentity(getTriggerKey())
    			                        .withSchedule(scheduleBuilder)
    			                        .build();
    	return trigger;
    }
	
}
