package com.example;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.domain.Student;
import com.example.service.StudentService;
import com.example.web.StudentController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentControllerTest {

    Logger log=LoggerFactory.getLogger(getClass());
	
	MockMvc mvc;
	/*计数*/
	int counter;
	
	@Autowired
	StudentService stuService;
	
	@Autowired
	StudentController stuController;
	
	@Before
	public void setUp(){
		mvc=MockMvcBuilders.standaloneSetup(stuController).build();
		/*采用下面方式会失败,因为new的controller没有注入所需要的StudentService*/
		//mvc=MockMvcBuilders.standaloneSetup(new StudentController()).build();
		counter=1;
		Student stu=new Student();
		stu.setId(1L);
		stu.setName("初始学生");
		stu.setSex("男");
		stu.setMsg("初始化");
		stuService.delAllStu();
		stuService.addStu(stu);
	}
	
	@Test
	public void test() throws Exception{
		log.info(stuService.findAllStu().toString());
		//测试UserController
		RequestBuilder request=null;
		request=get("/stu/");
		showinfo(request);
		request=post("/stu/")
				  .param("name", "张三")
				  .param("sex", "男")
				  .param("msg", "添加测试");
		showinfo(request);
		request=get("/stu/1");
		showinfo(request);
		request=put("/stu/1")
				  .param("name", "李四")
				  .param("sex", "女")
				  .param("msg", "更新测试");
		showinfo(request);
		request=delete("/stu/1");
		showinfo(request);
		request=get("/stu/msg/test");
		showinfo(request);
	}
	
	/**
	 * 将详细结果打印出来
	 * @param request 测试的request
	 */
	private void showinfo(RequestBuilder request){
		log.info("[{}]begin*****************************************************",counter);
		try {
			/*接收mvc.perform(request)结果  如果用mvc.perform(request).andReturn().getRequest()会重复访问被测试的url */
			MvcResult mvcResult= mvc.perform(request).andReturn();
			log.info("URL : [{}]",mvcResult.getRequest().getRequestURL());
			log.info("METHOD : [{}]",mvcResult.getRequest().getMethod());
			/*如果有参数,就把参数打印出来*/
			mvcResult.getRequest().getParameterMap().forEach((k,v)->{
				log.info("PARAM : [{}]=[{}]",k,Arrays.asList(v));
			});		
			log.info("RETURN : [{}]",mvcResult.getResponse().getContentAsString());
		} catch (Exception e) {
			log.info("EXCEPTION! : [{}]",e.getMessage());
			e.printStackTrace();
		}
		log.info("_____________________________________________________________");
		counter++;
	}
}
