package com.mytest.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Test16_javaurl {

	
	private final static String URL="http://www.baidu.com";
	private final static String JSON_URL="http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=218.4.255.255";
	
	public static void main(String[] args) throws Exception {		
		test(URL);	
		test(JSON_URL);
	}
	
	public static void test(String urll) throws Exception{
		URL url=new URL(urll);
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		connection.setRequestMethod("GET");
		InputStream is=connection.getInputStream();;
		BufferedReader reader=new BufferedReader(new InputStreamReader(is, "UTF-8"));
		String str=null;
		while((str=reader.readLine())!=null){
			System.out.println(str);
		}
	}
}
