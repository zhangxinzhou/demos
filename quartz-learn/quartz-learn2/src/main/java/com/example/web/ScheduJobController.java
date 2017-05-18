package com.example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.service.ScheduJobService;

@Controller
public class ScheduJobController {
	
	@Autowired
	ScheduJobService scheduJobService;

	@GetMapping({"/","jobs"})
	public String jobs(ModelMap mm){
		mm.put("data", scheduJobService.getStatupScheduleJobs());
		return "jobs";
	}
	
	@RequestMapping
	@ResponseBody
	public boolean addScheduJob(){
		return false;
	}
	
}
