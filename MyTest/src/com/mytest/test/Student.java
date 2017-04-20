package com.mytest.test;

/**
 * 学生实体类
 * @author ZXZ
 *
 */
public class Student {

	/*学生id*/
	private Long id;
	/*学生姓名*/
	private String name;
	/*学生性别*/
	private String sex;
	
	
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Student(Long id, String name, String sex) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
	}

	/*getter and setter*/
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", sex=" + sex + "]";
	}
	
}
