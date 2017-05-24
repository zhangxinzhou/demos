package com.mytest.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * 测试一些事情
 * @author ZXZ
 *
 */
public class Test02_objectslearn {

	/**
	 * 测试
	 * @param args
	 */
	public static void main(String[] args) {
		//objects可以判断对象是否为空,或者是否不为空
		String str=null;
		if(Objects.nonNull(str)){
			System.out.println(str + " is not null");
		}else{
			System.out.println(str + " is null");
		}
		
		String a="a";
		String b="a";
		String [] arr={"1","2"};
		String [] brr={"1","2"};
		//如果是list,顺序要一致,否则不相等
		List<String> l1=new ArrayList<>();
		List<String> l2=new ArrayList<>();
		l1.add("1");
		l1.add("2");
		//l2.add("1");
		l2.add("2");
		l2.add("1");
		//如果是set,set本事是无序的,无需关心此事
		Set<String> s1=new HashSet<>();
		Set<String> s2=new HashSet<>();
		s1.add("a");
		s1.add("b");
		s2.add("b");
		s2.add("a");
		equal(a,b);
		deepEqual(a, b);
		deepEqual(arr, brr);
		deepEqual(l1, l2);
		deepEqual(s1, s2);
	}
	
	/**
	 * 如果两个对象相等且不为null就打印出a=b,且都不为null
	 * @param a
	 * @param b
	 * @return boolean
	 */
	public static void equal(Object a,Object b){
		if(Objects.equals(a, b)&&Objects.nonNull(a)){
			System.out.println("两个对象相等,且都不为null");
		}else{
			System.out.println("两个对象不相等,或者为null");
		}
	}
	
	/**
	 * 比较两个对象(有可能是集合)是否相等
	 * @param a
	 * @param b
	 */
	public static void deepEqual(Object a,Object b){
		if(Objects.deepEquals(a, b)){
			System.out.println("两个对象(可能是集合)相等");
		}else{
			System.out.println("两个对象(可能是集合)不相等");
		}
	}
}
