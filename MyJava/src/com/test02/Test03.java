package com.test02;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * 设置一个起始年份,返回一个起始到现在的年份数组
 * @author user
 *
 */
public class Test03 {

	
	public static void main(String[] args) {
		getYears(2010);
	}
	
	//设置一个起始年份,返回一个起始到现在的年份数组
	public static void getYears(int start){
		List<Integer> result=new ArrayList<>();		
		Calendar c=Calendar.getInstance();
		int end=c.get(Calendar.YEAR);	
		result.add(start);
		while(end>start){
			start++;
			result.add(start);			
		}
		System.out.println(result);
	}
}
