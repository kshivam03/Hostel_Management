package com.hostelmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hostelmanagement.model.Staff;
import com.hostelmanagement.repository.StaffRepository;


@Service
public class StaffServiceImpl implements StaffService {
	
	@Autowired
	private StaffRepository staffRepository;
	
	@Override
	public List<Staff> getAllStaffs() {
		return staffRepository.findAll();
	}
	
	@Override
	public void saveStaff(Staff staff) {
		this.staffRepository.save(staff);
	}
	
	@Override
	public Staff getStaffById(long staff_id) {
		Optional<Staff> optional=staffRepository.findById(staff_id);
		
		Staff staff=null;
		
		if(optional.isPresent()) {
			staff=optional.get();
		}
		else {
			throw new RuntimeException("Staff not found for id :: " + staff_id);
		}
		
		return staff;
	}
	
	@Override
	public void deleteStaffById(long staff_id) {
		this.staffRepository.deleteById(staff_id);
	}
}
