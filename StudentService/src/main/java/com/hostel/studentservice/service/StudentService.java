package com.hostel.studentservice.service;


import com.hostel.studentservice.entities.Leave;
import com.hostel.studentservice.entities.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    Student saveStudent(Student student);
    Student getStudentById(int studentId);
    List<Student> getAllStudents();
    boolean deleteStudent(int studentId);
    Student updateStudent(Student student);
    Leave applyLeave(Leave leave);
    Leave getLeave(int leaveId);
    String deleteLeave(int leaveId);
    Leave updateLeave(Leave leave);
}
