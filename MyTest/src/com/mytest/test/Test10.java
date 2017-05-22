package com.mytest.test;

import java.util.ArrayList;
import java.util.List;

public class Test10 {

	private static ThreadLocal<Long> count=new ThreadLocal<>();
	
	public static void main(String[] args) {
		int num=10;
		test(num);
		test1(num);
	}
	
	/**
	 * n个学生围城一圈,从第0个学生开始报数,报数能被3整除的学生将退出圈,循环多次之后最后会剩下那个学生
	 * 1.先遍历一次圈,遍历完成之后再把能被3整除的学生T出圈
	 * 2.然后循环遍历上一步剩下的圈,直到圈的大小为1
	 * @param num
	 */
	public static void test(int num){	
		//数据准备
		List<Integer> list=new ArrayList<>(num);
		List<Integer> remove=new ArrayList<>();
		for (int i = 0; i < num; i++) {
			list.add(i);
		}
		count.set(0L);
		//开始遍历
		System.out.println(list);
		while(list.size()>1){			
			list.forEach(stu->{
				count.set(count.get()+1);
				if(count.get()%3==0){remove.add(stu);}
			});			
			list.removeAll(remove);
			remove.clear();
			System.out.println(list);
		}	
		System.out.println("报数报到 : "+count.get());
	}
	
	
	public static void test1(int num){	
		//数据准备
		List<Integer> list=new ArrayList<>(num);
		List<Integer> remove=new ArrayList<>();
		for (int i = 0; i < num; i++) {
			list.add(i);
		}
		int count=0;
		//开始遍历
		System.out.println(list);
		while(list.size()>1){			
			for (Integer integer : list) {
				count++;
				if(count%3==0){remove.add(integer);}
			}		
			list.removeAll(remove);
			remove.clear();
			System.out.println(list);
		}	
		System.out.println("报数报到 : "+count);
	}
}
