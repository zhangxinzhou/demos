package com.mytest.test;

/**
 * 如何防止空指针异常
 * String.valueOf(null);会抛出空指针异常的原因,是因为这个方法重载了,有两种参数类型
 * String.valueOf(char[] ch)和String.valueOf(Object obj),前者会抛出空指针异常
 * @author Administrator
 *
 */
public class Test19_ValueOfNNP {

	
	public static void main(String[] args) {
		test1(null);
		//test2(null);  //会抛出空指针异常
		//testNNP();    //会抛出空指针异常
		testNoNNP();
	}
	
	public static void test1(Object obj){
		String str=String.valueOf(obj);
		System.out.println(str);
	}
	
	public static void test2(char[] ch){
		String str=String.valueOf(ch);
		System.out.println(str);
	}
	
	public static void testNNP(){//这个方法会抛出空指针异常,因为实际调用的是String.valueOf(char[] ch)方法
		String str=String.valueOf(null);
		System.out.println(str);
	}
	
    public static void testNoNNP(){//String.valueOf(Object obj);就不会抛空指针异常(想知道原因就看源码),类似还有Integer.valueOf
    	String temp=null;
    	String str=String.valueOf(temp);
    	//String str=String.valueOf((Object)null);  //这样也行
    	System.out.println(str);
	}
}
