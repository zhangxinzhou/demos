package com.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test02 {

	public static void main(String[] args) {
		
		//test1();
		params("a","v","c","asc");

		String y=String.valueOf("abc");
		System.out.println(y);
	}
	

	public static void params(String ... str){
		String order=null;
		List<String> properties=new ArrayList<>();
		if(str!=null){
			if(str.length>0){
				for (String s : str) {
					if(s.equalsIgnoreCase("asc")){
						order="升序";
					}else if(s.equalsIgnoreCase("desc")){
						order="降序";
					}else{
						properties.add(s);
					}
				}
			}
		}
		StringBuffer sb=new StringBuffer("select * from table order by ");
		for (String s : properties) {
			sb.append(s+",");
		}
		int last=sb.length();
		sb.replace(last-1, last, " ");
		sb.append(order==null?"asc":order);
		System.out.println(sb.toString());
	}
	
	public static void test1(){
		//说明这种方式得到的list<String>只能看不能进行增删改操作
		List<String> l = Arrays.asList(new String[] { "ac", "abc", "df" });		
		l.remove(1);        //不允许移除,移除会报错
		l.add("test");      //不允许添加,添加会报错
		l.get(0);
		System.out.println(l);
	}
	
	public static void test2(){		
		List<String> l=new ArrayList<>();
		l.add("abc");     //可以增加
		l.add("a");
		l.add("c");
		l.remove(1);      //可以移除
		System.out.println(l);
	}
	
	public static void test3(){
		List<Son> sl=new ArrayList<>();
		sl.add(new Son());
		sl.add(new Son());
		sl.remove(0);
		sl.get(0).setFatherName("123");
	}
}
