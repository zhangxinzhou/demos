package com.example.init;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

/**
 * 如果spring是通过new的方式来初始化bean,那么就调用了该bean的无参构造方法.
 * 在调用构造方法方法之后紧接着会执行@PostConstruct的内容(调用构造方法紧接着执行的方法).
 * @author ZXZ
 *
 */
@Component
public class MyPostConstruct {
	
	

	/*
	 * spring 初始化bean将调用无参构造方法
	 */
	public MyPostConstruct() {
		System.out.println("我的构造方法被调用");
	}

	/*
	 * 调用构造方法之后执行的方法
	 */
	@PostConstruct
	public void doAfterConstruct(){
		System.out.println("我已经被初始化");
	}
		
	/*
	 * 销毁之前调用的方法
	 */
	@PreDestroy
	public void doBeforeDestroy(){
		System.out.println("我将被销毁");
	}
	
	
}
