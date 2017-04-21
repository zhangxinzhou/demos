package com.test02;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Test06 {

	public static void main(String[] args) throws Exception {
		
		
		Student s1=new Student("小白","1001",Sex.女);
		s1.sayPublic();
		//s1.sayPrivate(); 私有方法无法调用
		
		//调用私有方法
		Method[] methods=Student.class.getDeclaredMethods();
		Arrays.asList(methods).forEach(method->{
			if(method.getName().equals("sayPublic")||method.getName().equals("sayPrivate")){
				try {
					method.setAccessible(true);//允许访问私有方法
					method.invoke(s1);
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		});
		
		//调用私有属性
		Field field=Student.class.getDeclaredField("name");
		field.setAccessible(true);
		Object ob= field.get(s1);
		System.out.println(ob);
	}
}
