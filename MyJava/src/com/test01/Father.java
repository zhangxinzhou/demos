package com.test01;

public class Father extends GrandFather{

	private String fName;

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}
	
	public void say(){
		System.out.println("this is father");
	}
}
