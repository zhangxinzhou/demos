package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.model.ScheduleJob;
import com.example.repository.ScheduleJobRepository;

@Service
public class ScheduJobService {

	@Autowired
	ScheduleJobRepository scheduleJobRepository;
	
	public List<ScheduleJob> getScheduleJobs(){
		return scheduleJobRepository.findAll();
	}
	
	public List<ScheduleJob> getStatupScheduleJobs(){
		ScheduleJob sj=new ScheduleJob();
		sj.setJobStatus("1");
		Example<ScheduleJob> example=Example.of(sj);
		return scheduleJobRepository.findAll(example);
	}
}
