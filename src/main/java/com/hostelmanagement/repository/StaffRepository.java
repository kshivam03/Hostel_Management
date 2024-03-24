package com.hostelmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hostelmanagement.model.Staff;


@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

}
