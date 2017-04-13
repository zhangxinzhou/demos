package com.example.web;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.User;


@Controller
public class WebController {

	@RequestMapping
	public String test(){
		return "test";
	}
	
	@RequestMapping("/json")
	@ResponseBody
	public User[] json(@RequestBody User[] users){
		Arrays.asList(users).forEach(System.out::println);
		return users;
	}
	
	@RequestMapping("/json2/{msg}")
	@ResponseBody
	public User[] json2(@RequestBody User[] users,@PathVariable String msg){
		Arrays.asList(users).forEach(System.out::println);
		System.out.println(msg);
		return users;
	}
	
	@RequestMapping("/json3")
	@ResponseBody
	public List<User> json(@RequestBody List<User> users){
		Arrays.asList(users).forEach(System.out::println);
		return users;
	}
}
