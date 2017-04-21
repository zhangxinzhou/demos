package com.test02;

import java.util.ArrayList;
import java.util.List;


/**
 * 模拟计算pi和自然数E
 * @author user
 *
 */
public class Test00 {

	
	public static void main(String[] args) {
		System.out.println(Math.E);
		System.out.println(Math.PI);
		getE(100);
		getPI(100);
		getFS(20);
	}
	//自然数E
	public static void getE(int times){
		double result=1;
		for (int i = 0; i < times; i++) {
			result=result+result/times;
		}
		System.out.println(result);
	}
	//计算多边形的pi
	public static void getPI(int n){
		double result=n*Math.sin(Math.PI/n);
		System.out.println(result);
	}
	//Fibonacci sequence
	public static void getFS(int n){
		List<Integer> result=new ArrayList<>();	
		int num1=0;
		int num2=1;
		for (int i = 0; i < n; i++) {
			int temp=num1;
			num1=num2;
			num2=temp+num2;	
			if(i==0){result.add(num1);}
			result.add(num2);
		}
		System.out.println(result);
	}
	
}
