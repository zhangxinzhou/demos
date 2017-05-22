package com.mytest.test;

public class People {

	private Long pid;
	private String name;
	private Integer age;
	
	
	public People() {
		super();
	}
	public People(Long pid, String name, Integer age) {
		super();
		this.pid = pid;
		this.name = name;
		this.age = age;
	}
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "People [pid=" + pid + ", name=" + name + ", age=" + age + "]";
	}
	
	
}
