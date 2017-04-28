package com.example.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PaymentUtil {

	private static final Logger log=LoggerFactory.getLogger(PaymentUtil.class);
	
	public static String test(String target){
		log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>开始");
        try {
        	URL url=new URL(target);
    		HttpURLConnection huc=(HttpURLConnection)url.openConnection();		
    		huc.connect();
    		BufferedReader br=new BufferedReader(new InputStreamReader(huc.getInputStream()));
    		String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {// 循环读取流
                sb.append(line);
            }      
    		br.close();//关闭流
            huc.disconnect();// 断开连接  
            return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "{\"msg\":\"您输入的地址"+target+"不正确\"}";
	}
	
	public static void main(String[] args) {
		String target="https://www.baidu.com/";
		System.out.println(test(target));
	}
	
}
