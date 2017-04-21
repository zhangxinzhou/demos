package com.test;

public class chengfa {

	
	public static void main(String[] args) {
		System.out.println("************************************************");
		chengfa1();
		chengfa2();
		System.out.println("************************************************");
	}
	
	public static void chengfa1(){
		for (int i = 1; i <= 9; i++) {
			for (int j = 1; j <= i; j++) {
				System.out.print(j+"*"+i+"="+i*j+" ");
			}
			System.out.println();
		}
	}
	
	public static void chengfa2(){
		for (int i = 9; i >0; i--) {
			for (int j = i; j >0; j--) {
				System.out.print(i+"*"+j+"="+i*j+" ");
			}
			System.out.println();
		}
	}
}
