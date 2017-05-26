package com.mytest.test;

public class Test14_hashmap {

	
	public static void main(String[] args) {
		
		
		int hash=99;
		int length=8;
		indexFor(hash, length);
		mine(hash, length);
	}
	
	static void indexFor(int h, int length) {  //要保证length必须为2的次幂,否则取模(求余)的方法不准
	    int result= h & (length-1);  
	    System.out.println(result);
	}  

	static void mine(int h,int length){        //不需要保证length是2的次幂,但是效率地很多
		int result=h%length;
		System.out.println(result);
	}
}
