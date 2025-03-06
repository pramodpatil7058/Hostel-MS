package com.hostel.studentservice.service;

import com.hostel.studentservice.entities.Leave;
import com.hostel.studentservice.entities.Payment;
import com.hostel.studentservice.entities.Student;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 *
 * @author Pramod Patil
 * @version 1.0.0
 * @since 2025
 * @see JFKlkflflsdfl
 */


@Service
public interface StudentService {
	Student saveStudent(Student student);

	Student getStudent(int id);

	List<Student> getAllStudents();

	boolean deleteStudent(int studentId);

	Student updateStudent(Student student);

	Leave applyLeave(Leave leave);

	Leave getLeave(int leaveId);

	String deleteLeave(int leaveId);

	Leave updateLeave(Leave leave);

	Payment getPayment(int payId);

	Payment updatePayment(Payment payment);

	List<Payment> getPayments(int studentId);

	List<Leave> getLeavesByStudentId(int studentId);

	List<Payment> getPendingPayments(int studentId);

	List<Leave> getAllLeaves();
	
}
