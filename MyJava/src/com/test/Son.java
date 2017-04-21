package com.test;

public class Son extends Father{

	private String sonName;

	public String getSonName() {
		return sonName;
	}

	public void setSonName(String sonName) {
		this.sonName = sonName;
	}

	@Override
	public String toString() {
		return "Son [sonName=" + sonName + "]";
	}
	
}
