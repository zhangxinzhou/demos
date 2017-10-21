package com.example.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

	@RequestMapping("/demo")
	public String demo(){
		return "demo";
	}
	
	@RequestMapping("/demo1")
	public String demo1(){
		return "demo1";
	}
}
