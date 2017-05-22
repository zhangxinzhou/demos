package com.mytest.test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test11 {

	
	public static void main(String[] args) throws Exception {
		
		
		List<Student> stus=new ArrayList<>();
		List<People> peoples=new ArrayList<>();
		stus.add(new Student(1L, "张三", "男"));
		stus.add(new Student(2L, "李四", "女"));
		stus.add(new Student(3L, "李四", "女"));
		stus.add(new Student(4L, "李四", null));
		stus.add(new Student(5L, null, "男"));
		
		peoples.add(new People(1L, "p01", 10));
		peoples.add(new People(2L, "p02", 100));
		peoples.add(new People(3L, "p03", 110));
		test(stus);
		test(peoples);
	}
	
	public static void test(List<?> list) throws Exception{
		boolean flag = true;
		List<String> fieldNames = new ArrayList<>();		 
		for (Object object : list) {
			Field[] fields = object.getClass().getDeclaredFields();
			if (flag) {
				flag = false;
				Map<String, String> map=getMaps().get(object.getClass().getName());
				for (Field field : fields) {
					String fieldName=field.getName(); //属性值
					String tableHeadName=map.get(fieldName); //数据库中的字段整成用户能看懂的字段
					fieldNames.add(fieldName);					
					System.out.print(tableHeadName+"\t");
				}
				System.out.println();
			}			
			for (Field field : fields) {
				field.setAccessible(true);//可以访问私有属性
				System.out.print(field.get(object)+"\t"); 
			}
			System.out.println();
		}
	}
	
	public static Map<String, Map<String, String>> getMaps(){
		Map<String, Map<String, String>> map=new HashMap<>();
		map.put(People.class.getName(), getPeopleMap());
		map.put(Student.class.getName(),getStuMap());
		return map;
	}
	
	public static Map<String, String> getPeopleMap(){
		Map<String, String> map=new HashMap<>();
		map.put("pid", "艾迪");
		map.put("name", "姓名");
		map.put("age", "年龄");
		return map;
	}
	
	public static Map<String, String> getStuMap(){
		Map<String, String> map=new HashMap<>();
		map.put("id", "艾迪");
		map.put("name", "姓名");
		map.put("sex", "性别");
		return map;
	}
}
