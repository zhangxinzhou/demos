package com.example.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.domain.Student;
import com.example.service.StudentService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/test")
public class TestController {

	@Autowired
	StudentService stuService;
	
	@ApiOperation(value="测试test.html",notes="notes")
	@RequestMapping(value="/test",method=RequestMethod.GET)
	public String test(){
		return "test";
	}
	
	@ApiOperation(value="返回输入的msg",notes="notes")
	@ApiImplicitParam(name="msg",value="输入信息",required=true,dataType="String",paramType="query")
	@RequestMapping(value="/msg",method=RequestMethod.POST)
	@ResponseBody
	public String msg(String msg){
		return "您输入的是 : "+msg;
	}
	
	@ApiOperation(value="获取学生列表",notes="notes")
	@RequestMapping(value="/allstu",method=RequestMethod.GET)
	@ResponseBody
	public List<Student> allstu(){
		return stuService.findAllStu();
	}
	
	@ApiOperation(value="返回输入的学生信息",notes="notes")
	@RequestMapping(value="/student",method=RequestMethod.POST)
	@ResponseBody
	public Student student(@ModelAttribute Student stu){
		return stu;
	}
	
	
}
