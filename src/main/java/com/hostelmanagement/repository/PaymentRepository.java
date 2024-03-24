package com.hostelmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hostelmanagement.model.Payment;
import com.hostelmanagement.model.Student;


@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
	List<Payment> findByStudent(Student student);
}
