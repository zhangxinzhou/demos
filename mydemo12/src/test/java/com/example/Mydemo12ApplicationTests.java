package com.example;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Mydemo12ApplicationTests {

	@Autowired
	JdbcTemplate jdbc;
	
	@Test
	public void contextLoads() {
		String sql1="show tables";
		List<String> list1=jdbc.queryForList(sql1, String.class);
		System.out.println(list1);
		
		String sql2="select * from "+list1.get(0)+" limit 10";
		jdbc.queryForList(sql2).forEach(System.out::println);
		
	}

}
