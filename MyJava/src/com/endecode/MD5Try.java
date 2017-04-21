package com.endecode;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;



public class MD5Try {

	

	public static MessageDigest md5=null;
	public static String str=null;
	
	@BeforeClass
	public static void init() throws Exception{
		md5=MessageDigest.getInstance("MD5");		
	}
	
	@Before
	public void before(){
		System.out.println("-----------开始-------------");
		str="2";
	}
	
	@After
	public void after(){
		System.out.println("-----------结束-------------");
	}

	
	
	@Test
	public void EncodeByMd50() throws Exception{
        //String result=new String(md5.digest(str.getBytes()));
		char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'}; 
		md5.update(str.getBytes());
		byte[] md = md5.digest();
		int j = md.length;
        char str[] = new char[j * 2];
        int k = 0;
        for (int i = 0; i < j; i++) {
            byte byte0 = md[i];
            str[k++] = hexDigits[byte0 >>> 4 & 0xf];
            str[k++] = hexDigits[byte0 & 0xf];
        }
        String result=new String(str);
        System.out.println(result);
        System.out.println(Base64.getEncoder().encodeToString(result.getBytes()));
	}
	
	@Test
	public void EncodeByMd51() throws Exception{
		byte[] md = md5.digest(str.getBytes());
		String result= new BigInteger(1, md).toString(16);
        System.out.println(result);
        System.out.println(Base64.getEncoder().encodeToString(result.getBytes()));
	}


	@Test
	public void test(){
		Map<String, Object> map=new HashMap<>();
		int i=100;
		while(i-->0){
			map.put(i+"123", i+"123");
			System.out.println(map.hashCode());
		}
	}

}
