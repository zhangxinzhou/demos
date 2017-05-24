package com.mytest.test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * list元素数量和list容量的关系
 * 当元素数量>list容量的时候将会扩容,新的容量就旧容量的1.5倍new=old+(old>>1);
 * @author Administrator
 *
 */
public class Test13 {

	
	public static void main(String[] args) throws Exception {
		List<String> list=new ArrayList<>();
		int num=1000;
		int count=0;
		boolean flag=false;
		Field elementData= list.getClass().getDeclaredField("elementData");
		elementData.setAccessible(true);
		/*
		 * size:list元素数量
		 * capacity:list容量
		 * count:扩容次数
		 * nextcapacity:下一次扩容时,list容量
		 */
		System.out.println("size\tcapacity\tcount\tnextcapacity\t");
		while(list.size()<num){
			list.add("a");
			int size=list.size();
			int capacity=((Object[]) elementData.get(list)).length;
			int nextcapacity=capacity+(capacity>>1);//源码扩容方式
			if(flag){flag=false;count++;}
			if(size==capacity){flag=true;}//下一次将会扩容		
			System.out.println(size+"\t"+capacity+"\t"+count+"\t"+nextcapacity+"\t");
		}
	}
}
