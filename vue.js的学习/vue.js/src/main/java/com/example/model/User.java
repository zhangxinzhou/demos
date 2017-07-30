package com.example.model;

public class User {

	
	private Long id;
	private String name;
	private Integer age;
	private String remark;
		
	public User() {
		super();
	}
	public User(Long id, String name, Integer age, String remark) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.remark = remark;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + ", remark=" + remark + "]";
	}
	
}
