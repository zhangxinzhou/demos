package com.example.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.TestService;

@RestController
public class TestController {

	@Autowired
	TestService testService;
	
	
	@GetMapping("/{id}")
	public Map<String, Object> test(@PathVariable Long id){
		Map<String, Object> map=new HashMap<>();
		map.put("result", testService.test(id));
		map.putAll(testService.getT1andT2());
		return map;
	}
}
