package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity(name="my_tab2")
public class MyTab2 {

	@Id
	@GeneratedValue
	private Long id;
	private int num;
	@Column(nullable=false)
	private String name;
	private String message;
		
	public MyTab2() {
		super();
	}
	public MyTab2(Long id, int num, String name, String message) {
		super();
		this.id = id;
		this.num = num;
		this.name = name;
		this.message = message;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "Table1 [id=" + id + ", num=" + num + ", name=" + name + ", message=" + message + "]";
	}
}
