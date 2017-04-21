package com.test02;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Test05 {

	public static void main(String[] args) throws Exception {
		long m1=1024*1024;
		long maxMemory=Runtime.getRuntime().maxMemory();
		long freeMemory=Runtime.getRuntime().freeMemory();
		long totalMemory=Runtime.getRuntime().totalMemory();
		String cmd="ping baidu.com";
		Process process=Runtime.getRuntime().exec(cmd);
		InputStream is=process.getInputStream();
		BufferedReader br=new BufferedReader(new InputStreamReader(is,"gbk"));
		StringBuffer sb=new StringBuffer();
		String str=null;
		String enter=System.getProperty("line.separator");//换行符
		while((str=br.readLine())!=null){
			sb.append(str+enter);
		}
		br.close();
		is.close();
		System.out.println("最大内存:"+maxMemory/m1+"M");
		System.out.println("空闲内存:"+freeMemory/m1+"M");
		System.out.println("总共内存:"+totalMemory/m1+"M");
		System.out.println(sb.toString());
	}
}
