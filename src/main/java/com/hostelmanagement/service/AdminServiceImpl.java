package com.hostelmanagement.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hostelmanagement.model.Admin;
import com.hostelmanagement.repository.AdminRepository;


@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Override
	public void saveAdmin(Admin admin) {
		this.adminRepository.save(admin);
	}
	
	@Override
	public Admin getAdminById(String username) {
		Optional<Admin> optional=adminRepository.findById(username);
		
		Admin admin=null;
		
		if(optional.isPresent()) {
			admin=optional.get();
		}
		
		return admin;
	}
}

