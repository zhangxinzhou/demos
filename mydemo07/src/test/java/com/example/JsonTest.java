package com.example;

import java.util.List;
import org.junit.Test;
import com.alibaba.fastjson.JSON;
import com.example.model.User;


public class JsonTest {

	@Test
	public void test(){
		String str=getJsonStr();
		System.out.println(str);
		List<User> users=JSON.parseArray(str, User.class);
		users.forEach(System.out::println);
	}
	
	
	private String getJsonStr(){
		User user1 = new User(1L, "张1", "");  
        User user2 = new User(2L, "张2", "");  
        User user3 = new User(3L, "张3", "");  
        User user4 = new User(4L, "张4", "");
        User[] users = {user1, user2, user3, user4};  
        return JSON.toJSONString(users); 
	}
}
