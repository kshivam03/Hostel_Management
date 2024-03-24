package com.hostelmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.hostelmanagement.model.Student;
import com.hostelmanagement.repository.StudentRepository;


@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}
	
	@Override
	public void saveStudent(Student student) {
		this.studentRepository.save(student);
	}
	
	@Override
	public Student getStudentById(long student_id) {
		Optional<Student> optional=studentRepository.findById(student_id);
		
		Student student=null;
		
		if(optional.isPresent()) {
			student=optional.get();
		}
		else {
			throw new RuntimeException("Student not found for id :: " + student_id);
		}
		
		return student;
	}
	
	@Override
	public void deleteStudentById(long student_id) {
		this.studentRepository.deleteById(student_id);
	}
	
//	@Override
//	public Page<Student> listAll(int pageNo, String sortField, String sortDirection, String keyword) {
//		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
//				: Sort.by(sortField).descending();
//		
//		Pageable pageable = PageRequest.of(pageNo - 1, 3,sort);
//        if (keyword != null) {
//            return studentRepository.findAll(keyword,pageable);
//        }
//        return studentRepository.findAll(pageable);
//    }
	
	@Override
	public Page<Student> findPaginated(int pageNo, int pageSize) {
	 Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
	 return this.studentRepository.findAll(pageable);
	}
}
