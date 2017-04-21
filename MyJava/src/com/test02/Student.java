package com.test02;

public class Student {

	private String name;
	private String number;
	private Sex sex;
	
	
	public Student() {
		super();
	}	
	public Student(String name, String number, Sex sex) {
		super();
		this.name = name;
		this.number = number;
		this.sex = sex;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Sex getSex() {
		return sex;
	}
	public void setSex(Sex sex) {
		this.sex = sex;
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", number=" + number + ", sex=" + sex + "]";
	}
	public void sayPublic(){//
		System.out.println("i am "+this.name);
	}
	
	@SuppressWarnings("unused")
	private void sayPrivate(){
		System.out.println("i am "+this.name+", and i am a "+this.sex);
	}
	
}
