package com.endecode;

import java.util.Base64;

public class Base64Util {

	public static void main(String[] args) {
		String str="{\"name\":\"张三\",\"sex\":\"男\"}";
		String result=base64encode(str);
		System.out.println(result);
		System.out.println(base64decode(result));
	}
	
	
	
	public static String base64encode(String str){//编码
		//return new String(Base64.getEncoder().encode(str.getBytes()));		
		return Base64.getEncoder().encodeToString(str.getBytes());		
	}
	
	public static String base64decode(String str){//解码
		return new String(Base64.getDecoder().decode(str));
	}
}
