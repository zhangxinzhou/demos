package com.example.util;

import org.junit.Test;

public class ServiceUtil {

	
	public static void storageData(){
		String msg="将用户注册信息保存到数据库";
		CostTimeUtil.cost(1000, msg);
	}
	
	public static void sendEmail(){
		String msg="发送用户注册成功邮件";
		CostTimeUtil.cost(1000, msg);
	}
	
	public static void sendSMS(){
		String msg="发送用户注册成功短信";
		CostTimeUtil.cost(1000, msg);
	}
	
	@Test
	public void test(){
		long start=System.currentTimeMillis();
		storageData();
		sendEmail();
		sendSMS();
		String spendmsg=(System.currentTimeMillis()-start)+"ms";
		System.out.println("业务耗时 : "+spendmsg);
	}
}
