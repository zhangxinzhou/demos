package com.example.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Student;
import com.example.service.StudentService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/stu")
public class StudentController {

	@Autowired
	StudentService stuService;
    
	@ApiOperation(value="获取全部学生",notes="获取全部学生notes")
    @RequestMapping(value="/",method=RequestMethod.GET)
    public List<Student> getStuList(){
    	// 处理"/stu/"的GET请求,用来获取学生列表
    	// 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
    	return stuService.findAllStu();
    }
    
	@ApiOperation(value="添加学生",notes="根据student对象来创建学生")
	@ApiImplicitParam(name="student",value="实体student",required=true,dataType="Student")
    @RequestMapping(value="/",method=RequestMethod.POST)
    public Student postStu(@ModelAttribute Student stu){
    	// 处理"/stu/"的POST请求,用来创建student
    	// 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
    	return stuService.addStu(stu);
    }
    
	@ApiOperation(value="查询学生详细信息",notes="根据url的id获取详细信息")
	@ApiImplicitParam(name="id",value="学生id",required=true,dataType="Long",paramType="path")
    @RequestMapping(value="/{id}",method=RequestMethod.GET)
    public Student getStu(@PathVariable Long id){
    	// 处理"/stu/{id}"的GET请求,用来获取url中的id值的Student信息
    	// url中的id可通过@PathVariable绑定到函数的参数中
    	return stuService.getStuById(id);
    }
    
	@ApiOperation(value="更新学生详细信息",notes="根据url的id获取学生,并更新该学生的详细信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name="id",value="学生id",required=true,dataType="Long",paramType="path"),
		@ApiImplicitParam(name="student",value="学生实体信息student",required=true,dataType="Student")
	})
    @RequestMapping(value="/{id}",method=RequestMethod.PUT)
    public Map<String, Student> putStu(@PathVariable Long id, @ModelAttribute Student stu){
    	// 处理"/stu/{id}"的PUT请求，用来更新Student信息
		Map<String, Student> map=new HashMap<>();
    	Student temp=stuService.getStuById(id);
    	map.put("before", temp);
    	temp.setName(stu.getName());
    	temp.setSex(stu.getMsg());
    	temp.setMsg(stu.getMsg());   	
    	map.put("after", stuService.updateStu(stu));
    	return map;
    }
    
	@ApiOperation(value="删除学生",notes="根据url中的id删除该学生")
	@ApiImplicitParam(name="id",value="学生id",required=true,dataType="Long",paramType="path")
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public Map<String, List<Student>> deleteUser(@PathVariable Long id){
    	// 处理"/stu/{id}"的DELETE请求，用来删除User    	
    	Map<String, List<Student>> map=new HashMap<>();
    	map.put("before", stuService.findAllStu());
    	stuService.delStu(id);
    	map.put("after", stuService.findAllStu());
    	
    	return map;
    }
    
	@ApiOperation(value="返回信息",notes="测试用")
	@ApiImplicitParam(name="msg",value="字符串信息",required=true,dataType="String",paramType="path")
    @RequestMapping(value="/msg/{msg}",method=RequestMethod.GET)
    public String test(@PathVariable String msg){
    	return "msg : "+msg;
    }
}
