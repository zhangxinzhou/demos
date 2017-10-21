package com.example.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.TestService;

@RestController
public class TestController {

	@Autowired
	TestService testService;
	
	@GetMapping("/index")
	public String index(){
		return "index";
	}
	
	@GetMapping("/data1")
	public List<Map<String, Object>> data1(){		
		return testService.data1();
	}
	
}
