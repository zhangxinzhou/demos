package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.ScheduleJob;

public interface ScheduleJobRepository extends JpaRepository<ScheduleJob, Long>{

}
