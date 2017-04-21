package com.test;

public class Father {

	private Integer id;
	private String fatherName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	@Override
	public String toString() {
		return "Father [id=" + id + ", fatherName=" + fatherName + "]";
	}
	
}
