package com.hostel.wardenservice.service;

import com.hostel.wardenservice.entity.Leave;
import com.hostel.wardenservice.entity.Payment;
import com.hostel.wardenservice.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WardenServices {
    Student changeApplicationStatus(int studentId, boolean status);
    List<Student> getAllStudents(int size, int limit);
    Payment addPayment(Payment payment);
    String removeStudent(int studentId);
    List<Payment> getAllPendingPayments(int studentId);
    Leave getLeave(int leaveId);
    String deleteLeave(int leaveId);
    Leave updateLeave(Leave leave);

    List<Payment> getAllPayments();
	Student getStudent(int id);
}