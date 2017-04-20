package com.mytest.test;

import java.util.ArrayList;
import java.util.List;

public class Test06 {

	public static void main(String[] args) {
		List<Student> stus=new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			stus.add(new Student(Long.valueOf(i), "学生"+i, "m"));
		}
		
		System.out.println("**************************************全部");
		//打印全部的学生
		stus.forEach(System.out::println);
		
		System.out.println("**************************************break");
		//如果有学生的id为5,则终止
		for (Student stu : stus) {
			if(stu.getId()==5L){
				break;
			}
			System.out.println(stu);
		}
		
		System.out.println("**************************************continue");
		//跳过学生id为5的学生
		for (Student stu : stus) {
			if(stu.getId()==5L){
				continue;
			}
			System.out.println(stu);
		}
		
	}
}
