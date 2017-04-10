package com.example;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.web.UserController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

	Logger log=LoggerFactory.getLogger(getClass());
	
	MockMvc mvc;
	/*计数*/
	int counter;
	
	@Before
	public void setUp(){
		mvc=MockMvcBuilders.standaloneSetup(new UserController()).build();
		counter=1;
	}
	
	@Test
	public void testUserController() throws Exception{
		//测试UserController
		RequestBuilder request=null;
		
		// 1、get查一下user列表，应该为空 
		request=get("/users/");
		mvc.perform(request)
		        .andExpect(status().isOk())
		        .andExpect(content().string(equalTo("[]")));
		showinfo(request);
		
		// 2、post提交一个user
		request=post("/users/")
				  .param("id", "1")
				  .param("name", "测试大师")
		          .param("age", "20");
		mvc.perform(request)
		          .andExpect(content().string(equalTo("success")));
		showinfo(request);
		
		// 3、get获取user列表，应该有刚才插入的数据 
        request = get("/users/"); 
        mvc.perform(request) 
                .andExpect(status().isOk()) 
                .andExpect(content().string(equalTo("[{\"id\":1,\"name\":\"测试大师\",\"age\":20}]"))); 
        showinfo(request);

        // 4、put修改id为1的user 
        request = put("/users/1") 
                .param("name", "测试终极大师") 
                .param("age", "30"); 
        mvc.perform(request) 
                .andExpect(content().string(equalTo("success"))); 
        showinfo(request);

        // 5、get一个id为1的user 
        request = get("/users/1"); 
        mvc.perform(request) 
                .andExpect(content().string(equalTo("{\"id\":1,\"name\":\"测试终极大师\",\"age\":30}"))); 
        showinfo(request);

        // 6、del删除id为1的user 
        request = delete("/users/1"); 
        mvc.perform(request) 
                .andExpect(content().string(equalTo("success"))); 
        showinfo(request);

        // 7、get查一下user列表，应该为空 
        request = get("/users/"); 
        mvc.perform(request) 
                .andExpect(status().isOk()) 
                .andExpect(content().string(equalTo("[]"))); 
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
