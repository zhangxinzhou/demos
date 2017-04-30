package com.example.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.example.model.User;

 


@Controller
public class WebController {

	@RequestMapping
	@ResponseBody
	public String index(){
		return "index";
	}
	
	@RequestMapping("/test")
	public String test(HttpSession session){
		//为第四个测试做准备
		session.setAttribute("user_id", "fskl_dflkjsdfsl_1sf");
		return "test";
	}
	
	@RequestMapping("/get1")
	@ResponseBody
	public List<Object> test01(@RequestParam("str1") String str1,@RequestParam("Str2") String str2,
			                   @RequestParam("Str3") String str3,@RequestBody User [] users){
		List<Object> list=new ArrayList<>();
		list.add(str1);
		list.add(str2);
		list.add(str3);
		list.add(users);
		list.forEach(System.out::println);
		return list;
	}
	
	@RequestMapping("/get2/{str1}/{str2}/{str3}")
	@ResponseBody
	public List<Object> test02(@PathVariable String str1,@PathVariable String str2,
			                   @PathVariable String str3,@RequestBody User [] users){
		List<Object> list=new ArrayList<>();
		list.add(str1);
		list.add(str2);
		list.add(str3);
		list.add(users);
		list.forEach(System.out::println);
		return list;
	}

	@RequestMapping("/get3")
	@ResponseBody
	public Map<String, Object> test03(@RequestBody Map<String,Object> params) throws Exception{
		params.forEach((k,v)->{
			System.out.println("key : "+k+" value : "+v);
		});
		System.out.println(">>>>>>>>>>>>>>>>>>");
		String jsonStr=JSON.toJSONString(params.get("users"));
		System.out.println(jsonStr);
		List<User> users=JSON.parseArray(jsonStr, User.class);
		users.forEach(System.out::println);
		System.out.println(">>>>>>>>>>>>>>>>>>");
		return params;
	}
	
	@RequestMapping("/get4/{pathparam}")
	@ResponseBody
	public Map<String, Object> test04(@RequestHeader("Accept-Encoding") String encoding, 
                                      @RequestHeader("User-Agent") String User_Agent,
                                      @CookieValue(value="JSESSIONID",required=false) String cookie,
                                      @CookieValue(value="user_id",required=false) String user_id,
                                      @PathVariable String pathparam,
                                      @RequestBody String users){
		Map<String, Object> map=new HashMap<>();
		map.put("Accept-Encoding(@RequestHeader)", encoding);
		map.put("User-Agent(@RequestHeader)", User_Agent);
		map.put("JSESSIONID(@CookieValue)", cookie);
		map.put("user_id(@CookieValue)", user_id);
		map.put("pathparam(@PathVariable)", pathparam);
		map.put("users(@RequestBody)", users);
		map.forEach((k,v)->{
			System.out.println("key : "+k+" value : "+v);
		});
		return map;
	}
	
}
