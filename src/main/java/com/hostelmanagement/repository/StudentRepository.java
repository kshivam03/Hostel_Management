package com.hostelmanagement.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hostelmanagement.model.Room;
import com.hostelmanagement.model.Student;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	List<Student> findByRoom(Room room);
	
	public Page < Student > findAll(Pageable pageable);
}
