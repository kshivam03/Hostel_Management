package com.hostelmanagement.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.hostelmanagement.model.Student;


public interface StudentService {
	
	List<Student> getAllStudents();
	
	void saveStudent(Student student);
	
	Student getStudentById(long student_id);
	
	void deleteStudentById(long student_id);
	
	Page<Student> findPaginated(int pageNo, int pageSize);
}
