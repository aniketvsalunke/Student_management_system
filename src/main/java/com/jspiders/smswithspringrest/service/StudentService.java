package com.jspiders.smswithspringrest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jspiders.smswithspringrest.pojo.Student;
import com.jspiders.smswithspringrest.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;

	public Student addStudent(Student student) {
		return studentRepository.addStudent(student);
		
	}
	
	public List<Student> getAllStudents(){
		return studentRepository.getAllStudents();
	}
	
	public Student getStudentById(int id) {
		return studentRepository.getStudentById(id);
	}

	public Student updateStudent(Student student) {
		Student studentToBeUpdated = studentRepository.getStudentById(student.getId());
		if (studentToBeUpdated!=null) {
			studentToBeUpdated.setName(student.getName());
			studentToBeUpdated.setEmail(student.getEmail());
			studentToBeUpdated.setContact(student.getContact());
			studentToBeUpdated.setAge(student.getAge());
			studentRepository.addStudent(studentToBeUpdated);
		}
		return studentToBeUpdated;
		
	}
	
	public Student deleteStudent(int id) {
		return studentRepository.deleteStudent(id);
	}

}
