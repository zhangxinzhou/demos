package com.example.web;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.service.impl.TestService;

/**
 * TestController
 * @author ZXZ
 * @version 时间:2017-4-7
 */
@Controller
public class TestController {
	
	static final String TEST_SQL="select * from test";

	@Autowired
	TestService testService;
	
	@RequestMapping({"/","/index"})
	@ResponseBody
	public String index(){
		return "index";
	}
	
	@RequestMapping("/sql")
	@ResponseBody
	public List<Map<String, Object>> testSql(){
		return testService.testSql(TEST_SQL);
	}
	
	@RequestMapping("/count")
	@ResponseBody
	public int getCount(){
		return testService.getCount(TEST_SQL);
	}
	
	@RequestMapping("/id/{id}")
	@ResponseBody
	public List<Map<String, Object>> getById(@PathVariable Long id){
		String sql=TEST_SQL+" where id="+id;
		return testService.testSql(sql);
	}
	
	@RequestMapping("/msg/{msg}")
	@ResponseBody
	public List<Map<String, Object>> getByMsg(@PathVariable("msg") String str){
		String sql=TEST_SQL+" where field='"+str+"'";
		return testService.testSql(sql);
	}
	
	@RequestMapping("/test")
	public String test(ModelMap mm){
		mm.put("today", LocalDate.now());
		return "test";
	}
}
