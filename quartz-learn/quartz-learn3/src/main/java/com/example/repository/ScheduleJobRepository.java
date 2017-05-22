package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.ScheduleJob;

public interface ScheduleJobRepository extends JpaRepository<ScheduleJob, Long>{

}
