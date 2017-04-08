package com.example.service;

import java.util.List;

import com.example.domain.Student;

public interface StudentService {

	
	List<Student> findAllStu();
	
	Student addStu(Student stu);
	
	Student getStuById(Long id);
	
	Student updateStu(Student stu);
	
	void delStu(Long id);
	
	void delAllStu();
}
