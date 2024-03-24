package com.hostelmanagement.service;

import java.util.List;

import com.hostelmanagement.model.Staff;


public interface StaffService {
	
	List<Staff> getAllStaffs();
	
	void saveStaff(Staff staff);
	
	Staff getStaffById(long staff_id);
	
	void deleteStaffById(long staff_id);
}
