package com.example.util;

import org.junit.Test;

public class CostTimeUtil {

	
	//模拟处理某个业务花费时间的方法
	public static void cost(long millis,String msg){
		System.out.println(">>>>>>>>>>>>>>>>>>>>>"+msg+"->开始执行");
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(">>>>>>>>>>>>>>>>>>>>>"+msg+"->执行完毕");
	}
	
	
	@Test
	public void test(){
		cost(1000,"发短信");
	}
}
