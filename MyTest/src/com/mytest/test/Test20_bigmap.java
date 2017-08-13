package com.mytest.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test20_bigmap {

	
	private static final int MAP_SIZE=500;
	private static final int LIST_SIZE=5000;
	
	public static void main(String[] args) {
		long start = getMemory();
		System.out.println("初始memory:"+start+"MB");
		List<Map<String, Object>> data = getList();	
		System.out.println("数据条数:"+data.size());
		System.out.println("每条数据的字段数:"+data.get(0).size());
		long end = getMemory();
		System.out.println("然后memory:"+end+"MB");
		System.out.println("消耗memory:"+(end-start)+"MB");
		
		
		
	}
	
	private static long getMemory(){
		return Runtime.getRuntime().totalMemory()/1024/1024;
	}
	
	private static Map<String, Object> getMap(){
		Map<String, Object> map=new HashMap<>();
		for (int i = 0; i < MAP_SIZE; i++) {
			map.put("key"+i, "value"+1);
		}
		return map;
	}
	
	private static List<Map<String, Object>> getList(){
		List<Map<String, Object>> list = new ArrayList<>();
		for (int i = 0; i < LIST_SIZE; i++) {
			list.add(getMap());
		}
		return list;
	}
}
