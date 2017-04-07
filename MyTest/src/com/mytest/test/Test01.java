package com.mytest.test;

public class Test01 {

	public static void main(String[] args) {
		Student stu=new Student();
		stu.setId(100L);
		stu.setName("测试");
		stu.setSex("男");
		Student stu2=new Student();
		stu2.setId(-stu.getId());
		stu2.setName(stu.getName());
		stu2.setSex(stu.getSex());
		System.out.println(stu2);
	}
}
