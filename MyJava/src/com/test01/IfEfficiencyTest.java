package com.test01;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class IfEfficiencyTest {
	
	private Long times;

	@BeforeClass
	public static void before(){
		System.out.println("start");		
	}
	
	@AfterClass
	public static void after(){
		System.out.println("end");
	}  
	
	@Before
	public void bf(){
		times=10000000l;
	}

	
	@Test
	public void test01(){
		long start=System.nanoTime();
		while(times-->0){
			if(times%10==9){}
		}
		long end=System.nanoTime();
		long spend=end-start;
		System.out.println("cost:"+spend+"ns");
	}
	
	@Test
	public void test02(){
		long start=System.nanoTime();
		while(times-->0){
			if(times%10<9){}
			//if(times%10==9){}
		}
		long end=System.nanoTime();
		long spend=end-start;
		System.out.println("cost:"+spend+"ns");
	}

}
