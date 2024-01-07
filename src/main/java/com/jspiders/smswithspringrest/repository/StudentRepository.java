package com.jspiders.smswithspringrest.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jspiders.smswithspringrest.pojo.Student;

@Repository
public class StudentRepository {

	@Autowired
	private EntityManager entityManager;
	
	public Student addStudent(Student student) {
		EntityTransaction entityTransaction=entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(student);
		entityTransaction.commit();
		return student;
	}
	
   @SuppressWarnings("unchecked")
    public List<Student>getAllStudents(){
	   Query query=entityManager.createQuery("select student from Student student");
	    return query.getResultList();
   }
   
   public Student getStudentById(int id) {
	   EntityTransaction entityTransaction=entityManager.getTransaction();
	   entityTransaction.begin();
	   Student student = entityManager.find(Student.class, id);
	   entityTransaction.commit();
	   return student;
   }

public Student deleteStudent(int id) {
	EntityTransaction entityTransaction=entityManager.getTransaction();
	entityTransaction.begin();
	Student student = entityManager.find(Student.class, id);
	entityManager.remove(student);
	entityTransaction.commit();
	return student;
	
}
	
	

}
