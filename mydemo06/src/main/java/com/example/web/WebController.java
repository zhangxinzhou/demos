package com.example.web;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.service.MQService;
import com.example.service.NormalService;

@Controller
public class WebController {
	
	@Autowired
	NormalService normalService;
	@Autowired
	MQService mqService;
	

	//主页
	@RequestMapping
	@ResponseBody
	public String index(){
		return "index";
	}
	
	//模拟普通串行执行业务
	@RequestMapping("/normal")
	@ResponseBody
	public String normal(){
		long start=System.currentTimeMillis();
		//执行业务
		boolean result=normalService.doNormalService();
		String spendmsg=(System.currentTimeMillis()-start)+"ms";
		return result+"!耗时:"+spendmsg;
	}
	
	//模拟用队列的方式执行业务
	@RequestMapping("/mq")
	@ResponseBody
	public String mq(){
		long start=System.currentTimeMillis();
		//执行业务
		boolean result=mqService.doMQService();
		String spendmsg=(System.currentTimeMillis()-start)+"ms";
		return result+"!耗时:"+spendmsg;
	}
	
	//测试发送一万条消息到队列
	@RequestMapping("/test")
	@ResponseBody
	public String test(){
		mqService.test();
		return "test";
	}
}
