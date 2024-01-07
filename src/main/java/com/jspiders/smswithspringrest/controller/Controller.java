package com.jspiders.smswithspringrest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jspiders.smswithspringrest.pojo.Student;
import com.jspiders.smswithspringrest.response.StudentResponse;
import com.jspiders.smswithspringrest.service.StudentService;

@org.springframework.stereotype.Controller
@ResponseBody
public class Controller {
	
	@Autowired
	private  StudentService studentService;
	
	
	@PostMapping(path = "/student" ,consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StudentResponse> addStudent(@RequestBody Student student) {
		Student addStudent = studentService.addStudent(student);
		StudentResponse studentResponse=new StudentResponse();
		studentResponse.setMessage("stuedent added..!");
		studentResponse.setStudent(addStudent);
		studentResponse.setStudents(null);
		studentResponse.setStatus(HttpStatus.CREATED.value());
		return new ResponseEntity<StudentResponse>(studentResponse, HttpStatus.CREATED);
		
	}
	
	@GetMapping(path = "/students" ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StudentResponse>getAllStudents(){
		List<Student> students = studentService.getAllStudents();
		if (students != null && students.size()>0) {
			StudentResponse studentResponse =new StudentResponse();
			studentResponse.setMessage("students fetched");
			studentResponse.setStudent(null);
			studentResponse.setStudents(students);
			studentResponse.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<StudentResponse>(studentResponse, HttpStatus.FOUND);
		}else {
			StudentResponse studentResponse =new StudentResponse();
			studentResponse.setMessage("students not fetched");
			studentResponse.setStudent(null);
			studentResponse.setStudents(null);
			studentResponse.setStatus(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<StudentResponse>(studentResponse, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(path = "/student",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StudentResponse>getStudentById(@RequestParam int id){
		Student student = studentService.getStudentById(id);
		if (student != null) {
			StudentResponse studentResponse=new StudentResponse();
			studentResponse.setMessage("student fetched..");
			studentResponse.setStudent(student);
			studentResponse.setStudents(null);
			studentResponse.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<StudentResponse>(studentResponse, HttpStatus.FOUND);
		}else {
			StudentResponse studentResponse=new StudentResponse();
			studentResponse.setMessage("student not fetched..");
			studentResponse.setStudent(null);
			studentResponse.setStudents(null);
			studentResponse.setStatus(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<StudentResponse>(studentResponse, HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping(path = "/student",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StudentResponse>updateStudent(@RequestBody Student student){
		Student updateStudent = studentService.updateStudent(student);
		if (updateStudent != null) {
			StudentResponse studentResponse=new StudentResponse();
			studentResponse.setMessage("student is updated");
			studentResponse.setStudent(updateStudent);
			studentResponse.setStudents(null);
			studentResponse.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<StudentResponse>(studentResponse, HttpStatus.CREATED);
		}else {
			StudentResponse studentResponse=new StudentResponse();
			studentResponse.setMessage("student is not updated");
			studentResponse.setStudent(null);
			studentResponse.setStudents(null);
			studentResponse.setStatus(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<StudentResponse>(studentResponse, HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping(path = "/student/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StudentResponse> deleteStudent(@PathVariable int id){
		Student student = studentService.deleteStudent(id);
		if (student != null) {
			StudentResponse studentResponse=new StudentResponse();
			studentResponse.setMessage("student deleted..");
			studentResponse.setStudent(student);
			studentResponse.setStudents(null);
			studentResponse.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<StudentResponse>(studentResponse, HttpStatus.OK);
		}else {
			StudentResponse studentResponse=new StudentResponse();
			studentResponse.setMessage("student not deleted..");
			studentResponse.setStudent(null);
			studentResponse.setStudents(null);
			studentResponse.setStatus(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<StudentResponse>(studentResponse, HttpStatus.NOT_FOUND);
		}
	}
}
