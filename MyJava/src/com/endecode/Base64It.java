package com.endecode;


import it.sauronsoftware.base64.Base64;

public class Base64It {

	

	public static void main(String[] args) {
		
		
		String str="{\"name\":\"张三\",\"sex\":\"男\"}";
		String encodedStr = Base64.encode(str);
		String decodedStr = Base64.decode(encodedStr);
		System.out.println("Base64编码后:"+encodedStr);        //
		System.out.println("Base64解码后:"+decodedStr);
		
		
		int i=3;
		String encode10=base64encode(str,i);
		String decode10=base64decode(encode10,i);
		System.out.println("Base64编码"+i+"次:"+encode10);
		System.out.println("Base64解码"+i+"次:"+decode10);
	}
	
	
	
	public static String base64encode(String str,int i){
		while(i>0){
			i--;
			str=Base64.encode(str);
		}
		return str;
	}
	
	public static String base64decode(String str,int i){
		while(i>0){
			i--;
			str=Base64.decode(str);
		}
		return str;
	}

}
