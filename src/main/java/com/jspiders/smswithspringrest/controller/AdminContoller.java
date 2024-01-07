package com.jspiders.smswithspringrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jspiders.smswithspringrest.pojo.Admin;
import com.jspiders.smswithspringrest.response.AdminResponse;
import com.jspiders.smswithspringrest.service.AdminService;

@Controller
@ResponseBody
public class AdminContoller {
	
	@Autowired
	private AdminService adminService;
	
	@PostMapping(path = "/admin",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AdminResponse>addAdmin(@RequestBody Admin admin){
		Admin addAdmin = adminService.addAdmin(admin);
		AdminResponse adminResponse=new AdminResponse();
		adminResponse.setMessage("signed up..");
		adminResponse.setAdmin(addAdmin);
		adminResponse.setStatus(HttpStatus.CREATED.value());
		return new ResponseEntity<AdminResponse>(adminResponse, HttpStatus.CREATED);
		
	}
	
	@PostMapping(path = "/login" ,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AdminResponse> login(@RequestBody Admin admin){
		Admin adminTobeloggedInWithPasswordEmail = adminService.login(admin);
		if (adminTobeloggedInWithPasswordEmail != null) {
			AdminResponse adminResponse=new AdminResponse();
			adminResponse.setMessage("logged_in..");
			adminResponse.setAdmin(adminTobeloggedInWithPasswordEmail);
			adminResponse.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<AdminResponse>(adminResponse, HttpStatus.FOUND);
		}else {
			AdminResponse adminResponse=new AdminResponse();
			adminResponse.setMessage("invalid email or password.?");
			adminResponse.setAdmin(null);
			adminResponse.setStatus(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<AdminResponse>(adminResponse, HttpStatus.NOT_FOUND);
		}
	}

}
