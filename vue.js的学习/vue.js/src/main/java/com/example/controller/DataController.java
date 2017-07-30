package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.User;

@RestController("/data")
public class DataController {

	@RequestMapping("/user")
	public User getUser(){
		return new User(1L, "张三", 10, "getUser");
	}
}
