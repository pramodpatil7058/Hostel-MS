package com.hostel.wardenservice.externalServices;

import com.hostel.wardenservice.entity.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@FeignClient("STUDENTSERVICE")
public interface StudentServices {
    @GetMapping("student/getStudentById/{id}")
    Student getStudent(@PathVariable("id") int studentId);

    @PutMapping("student/updateStudent")
    Student updateStudent(Student student);

    @GetMapping("student/getAllStudents")
    List<Student> getAllStudents();

    @DeleteMapping("student/deleteStudent/{id}")
    boolean deleteStudent(@PathVariable("id") int studentId);
}
