package com.example.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class TestService {

	public List<Map<String, Object>> data1(){		
		List<Map<String, Object>> result=new ArrayList<>();
		Map<String, Object> map=new HashMap<>();
		map.put("sdf", "dfs");
		result.add(map);
		return result;
	}
}
