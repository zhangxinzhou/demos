package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Student;
import com.example.domain.repository.StudentRepository;
import com.example.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepository stuRepository;
	
	@Override
	public List<Student> findAllStu() {
		return stuRepository.findAll();
	}

	@Override
	public Student addStu(Student stu) {
		return stuRepository.save(stu);
	}

	@Override
	public Student getStuById(Long id) {
		return stuRepository.findOne(id);
	}

	@Override
	public Student updateStu(Student stu) {
		return stuRepository.save(stu);
	}

	@Override
	public void delStu(Long id) {
		stuRepository.delete(id);;
	}

	@Override
	public void delAllStu() {
		stuRepository.deleteAll();
	}

}
