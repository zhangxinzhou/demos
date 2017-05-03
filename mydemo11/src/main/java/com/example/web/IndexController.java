package com.example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.service.TestService;

@Controller
public class IndexController {
	
	@Autowired
	TestService testService;

	@RequestMapping
	@ResponseBody
	public String index(){
		return "index";
	}
	
	@RequestMapping("/seckill")
	@ResponseBody
	public void Seckill(Integer num){
		if(num==null){
			num=100;
		}
		testService.Seckill(num);
	}
}
