package com.example;

public class Test {

	
	public static void main(String[] args) throws InterruptedException {
		String str="abc";
		Thread.sleep(1000);
		System.out.println(str);
		Thread.sleep(1000);
		System.out.println(str);
		System.out.println("finish");
	}
}
