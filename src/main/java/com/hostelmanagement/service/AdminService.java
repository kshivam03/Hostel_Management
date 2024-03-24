package com.hostelmanagement.service;

import com.hostelmanagement.model.Admin;


public interface AdminService {
		
	void saveAdmin(Admin admin);
	
	Admin getAdminById(String username);
}
