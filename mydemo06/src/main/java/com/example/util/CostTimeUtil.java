package com.example.util;

public class CostTimeUtil {

	
	//模拟处理某个业务花费时间的方法
	public static void sleep(long millis){
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
