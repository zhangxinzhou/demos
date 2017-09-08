package com.test;

import java.math.BigDecimal;

public class Test03 {

		
	public static void main(String[] args) {
		
		
		
		BigDecimal bd=new BigDecimal("3.1415926");		
		System.out.println(bd.setScale(5,BigDecimal.ROUND_HALF_UP));
		tttt(100, BigDecimal.valueOf(1.1));
		ttttt(10000, 365*1000, BigDecimal.valueOf(1.0001));
	}
	
	private static void tttt(int n,BigDecimal rote){
		BigDecimal result=rote.pow(n).setScale(2, BigDecimal.ROUND_HALF_UP);
		System.out.println(result);
	}
	
	private static void ttttt(int money,int days,BigDecimal rote){
		BigDecimal result=rote.pow(days).setScale(5, BigDecimal.ROUND_HALF_UP);
		System.out.println(result.multiply(new BigDecimal(money)));
	}
}
