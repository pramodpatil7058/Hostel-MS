package com.hostel.wardenservice.external_services;

import com.hostel.wardenservice.entity.Student;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("STUDENTSERVICE")
public interface StudentServices {
	@GetMapping("student/getStudentById/{id}")
	Student getStudent(@PathVariable("id") int studentId) throws Exception;

	@PutMapping("student/updateStudent")
	Student updateStudent(Student student) throws Exception;

	@GetMapping("student/getAllStudents")
	List<Student> getAllStudents(@RequestParam int size, @RequestParam int limit) throws Exception;

	@DeleteMapping("student/deleteStudent/{id}")
	boolean deleteStudent(@PathVariable("id") int studentId) throws Exception;
}
