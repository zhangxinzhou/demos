package com.example.web;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

	@Value("${spring.profiles.active}")
	String configStr;
	
	@GetMapping
	public String index(){
		return "当前采用的配置是:"+configStr;
	}
}
