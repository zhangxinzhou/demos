package com.test01;

import java.util.List;

public class TTTT {

	public static void main(String[] args) {
		Son son=new Son();
		Father fa=new Father();
		faTest(fa);
		faTest(son);
		
		sonTest(son);
		//sonTest(fa); 报错
	}
	
	public static void faTest(Father fa){
		System.out.println(fa);
	}
	
	public static void sonTest(Son son){
		System.out.println(son);
	}
	
	public static void Textends1(List<? extends Father> l){
		System.out.println(l);
	}
	
	public static  List<? extends Father> Textends2(List<? extends Father> l){
		return l;
	}
	
	public static <T>  void Textends2(T t){
		System.out.println(t);
	}
	
	public static <T>  T Textends3(T t){
		return t; 
	}
	
	public static <T1,T2> void Textends4(T1 t1,T2 t2){
		System.out.println(t1);
		System.out.println(t2);
	}
	
	public static <T1,T2> T1 Textends5(T1 t1,T2 t2){
		return t1;
	}
	
	public static  void Textends6(Father t){		
		System.out.println(t);
	}
	
	public static void Tsuper1(List<? extends Father> l){
		System.out.println(l);
	}
	
	
	
	
	
}
