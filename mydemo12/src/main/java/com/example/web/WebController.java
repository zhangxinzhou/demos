package com.example.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.service.TestService;

@Controller
public class WebController {

	@Autowired
	TestService testService;
	
	@RequestMapping
	public String index(){
		return "index";
	}
	
	@RequestMapping("tables")
	public String tables(ModelMap mm){
		mm.put("data",testService.getAllTablesDetail());
		return "tables";
	}
	
	@RequestMapping("table")
	public String table(ModelMap mm,String tablename){
		mm.put("data",testService.getTableByTablename(tablename));
		return "table";
	}
	
	
	
	@RequestMapping("/gettablenames")
	@ResponseBody
	public List<String> getAllTablenames(){
		return testService.getAllTables();
	}
	
	@RequestMapping("/gettable")
	@ResponseBody
	public List<Map<String, Object>> getTableByTablename(String tablename){
		return testService.getTableByTablename(tablename);
	}
}
