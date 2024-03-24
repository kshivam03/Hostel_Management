package com.hostelmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hostelmanagement.model.Admin;


@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {
	
}
