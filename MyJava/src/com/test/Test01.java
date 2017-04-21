package com.test;

import java.util.HashMap;

public class Test01 {

	public static void main(String[] args) {
	
		/*half(10);
		
		
		//没问题,父类(小范围)=子类(大范围)
		Son s=new Son();
		Father f=s;
		System.out.println(f);
		//会报错,子类=父类会报错
		Father fa=new Father();
		Son so=(Son) fa;
		System.out.println(so);*/
		
		
		test2();
	}
	
	public static void half(int num){
		int n=0;
		while(num!=0){
			num=num/2;
			n++;
		}
		System.out.println("在第"+n+"次变0了");
	}
	

	public static void test2(){
		HashMap<String, String> map=new HashMap<>();
		map.put("abc", "cde");
		System.out.println(map.get("abc"));
	}

	
}
