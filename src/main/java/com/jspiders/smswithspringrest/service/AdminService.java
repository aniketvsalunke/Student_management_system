package com.jspiders.smswithspringrest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jspiders.smswithspringrest.pojo.Admin;
import com.jspiders.smswithspringrest.repository.AdminRepository;

@Service
public class AdminService {
	
	@Autowired
	private AdminRepository adminRepository;

	public Admin addAdmin(Admin admin) {
		
		return adminRepository.addAdmin(admin);
		
	}

	public Admin login(Admin admin) {
		List<Admin> admins = adminRepository.getAdmins();
		Admin admintobeloggedIn=null;
		for (Admin a : admins) {
			if (admin.getEmail().equals(a.getEmail()) && admin.getPassword().equals(a.getPassword())) {
				admintobeloggedIn=a;
			}
		}
		return admintobeloggedIn;
		
	}

}
