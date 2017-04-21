package com.test02;

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 建立组别与产品的对应关系,根据组别能查出产品的编码
 * @author user
 *
 */
public class Test01 {

	private static Map<String, String> map=new HashMap<>();
	
	@BeforeClass
	public static void init(){
		//组别 <> 产品 的对应关系
		map.put("B3_N4A", "PS111111");
		map.put("B3_N4B", "PS111111");
		map.put("B3_N4C", "PS222222");
		map.put("B3_N4D", "PS333333");
	}
	
	@Test
	public void test(){
		String location="B3_N4A_W2";
		System.out.println("位置:"+location+",应得到的pdf:"+getPdfNameByLocation(location));
	}
	
	private String getPdfNameByLocation(String location){
		String result=null;		
		if(location!=null){
			String[] str=location.split("_");
			if(str.length==3){//location的格式满足要求
				String BNum=str[0];
				String GNum=str[1];
				String WNum=str[2];
				String ProductName=map.get(BNum+"_"+GNum);
				if(ProductName!=null){
					result=ProductName+"_"+WNum+".pdf";
				}
			}
		}
		return result;
	}
}
