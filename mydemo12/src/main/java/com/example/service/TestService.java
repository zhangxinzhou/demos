package com.example.service;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class TestService {

	@Autowired
	JdbcTemplate jdbc;
	
	//
	public List<String> getAllTables(){
		String sql="show tables";
		return jdbc.queryForList(sql,String.class);
	}
	
	//获取当前数据库下所有的表名
	public List<Map<String, Object>> getAllTablesDetail(){
		String sql="select TABLE_NAME,TABLE_SCHEMA,CREATE_TIME,UPDATE_TIME from information_schema.tables where TABLE_SCHEMA=database()";
		return jdbc.queryForList(sql);
	}
	
	public List<Map<String, Object>> getTableByTablename(String tablename){
		String sql="select * from "
	               +tablename
	               +" limit 10";
		return jdbc.queryForList(sql);
	}
	

}
