package com.example.test;



public class Test02 {

	private static boolean flag=true;
	
	public static void main(String[] args) {
		while(flag){
			flag=false;
			System.out.println("只运行一次");
		}
	}
}
