package com.example.web;

import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.model.User;

@Controller
public class WebController {

	@RequestMapping
	@ResponseBody
	public String index(){
		return "index";
	}
	
	@RequestMapping("/test")
	public String test(){
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
}
