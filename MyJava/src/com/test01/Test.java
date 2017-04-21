package com.test01;

public class Test {

	
	public static void main(String[] args) {
		String str="10074590536352";
		Long num=10074590536352l;
		int times=1000000;
		test01(str, str, times);
		test02(num, num, times);
	}
	
	
	public static void test01(String str1,String str2,int i){
		Long t=System.nanoTime();
		int x=0;
		while(i-->0){
			if(str1.equals(str2)){
				x++;
			}
		}
		System.out.println(x);
		System.out.println("cost1:"+(System.nanoTime()-t)+"ns");
	}
	
	public static void test02(Long num1,Long num2,int i){
		Long t=System.nanoTime();
		int x=0;
		while(i-->0){
			if(num1==num2){
				x++;
			}
		}
		System.out.println(x);
		System.out.println("cost2:"+(System.nanoTime()-t)+"ns");
	}
}
