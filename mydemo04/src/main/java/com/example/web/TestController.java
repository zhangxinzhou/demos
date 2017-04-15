package com.example.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

	@RequestMapping("/testurl")
	@ResponseBody
	public String test(){
		return "this is a test";
	}
	
	@RequestMapping({"/","/index"})
	public String index(){
		return "index";
	}
}
