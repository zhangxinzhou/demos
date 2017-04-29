package com.example.model;

public class User {

	private Long id;
	private String name;
	private String msg;
	
	
	public User() {
		super();
	}
	
	public User(Long id, String name, String msg) {
		super();
		this.id = id;
		this.name = name;
		this.msg = msg;
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
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", msg=" + msg + "]";
	}
	
	
}
