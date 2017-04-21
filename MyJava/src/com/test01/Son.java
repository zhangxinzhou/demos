package com.test01;

public class Son extends Father{

	private String sName;

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}
	
	public void say(){
		System.out.println("this is son");
	}
}
