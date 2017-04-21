package com.test02;

import java.util.Arrays;

public class Test09 {

	
	public static void main(String[] args) {
		String classname=Thread.currentThread().getStackTrace()[1].getClassName();
		String methodname=Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("类名:"+classname+",方法名:"+methodname);
		
		StackTraceElement[] ste=Thread.currentThread().getStackTrace();
		Arrays.asList(ste).forEach(s->{
			System.out.println("类名:"+s.getClassName());
			System.out.println("方法名:"+s.getMethodName());
			System.out.println("文件名:"+s.getFileName());
			System.out.println("行号:"+s.getLineNumber());
		});
		
	}
	
}
 