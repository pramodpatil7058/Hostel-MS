package com.hostel.wardenservice.service.impl;

import com.hostel.wardenservice.entity.Leave;
import com.hostel.wardenservice.entity.Payment;
import com.hostel.wardenservice.entity.Student;
import com.hostel.wardenservice.exception.ResourceNotFoundException;
import com.hostel.wardenservice.external_services.LeaveServices;
import com.hostel.wardenservice.external_services.PaymentServices;
import com.hostel.wardenservice.external_services.StudentServices;
import com.hostel.wardenservice.service.WardenServices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WardenServicesImpl implements WardenServices {

	private StudentServices studentServices;

	private PaymentServices paymentServices;

	private LeaveServices leaveServices;

	public WardenServicesImpl(StudentServices studentServices, PaymentServices paymentServices,
			LeaveServices leaveServices) {
		this.leaveServices = leaveServices;
		this.paymentServices = paymentServices;
		this.studentServices = studentServices;
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	// ? Dealing with StudentService
	@Override
	public Student changeApplicationStatus(int studentId, boolean status) {
		logger.info("Change application status of student id {} to {}", studentId, status);
		try {
			Student student = studentServices.getStudent(studentId);
			student.setStatus(status);
			return studentServices.updateStudent(student);
		} catch (Exception e) {
			System.out.println("Student does not exist");
			throw new ResourceNotFoundException(e.getMessage());
		}

	}

	@Override
	public List<Student> getAllStudents(int size, int limit) {
		logger.info("Get all students");
		try {
		return studentServices.getAllStudents(size, limit);
		}catch(Exception e) {
			throw new ResourceNotFoundException(e.getMessage());
		}
	}

	@Override
	public String removeStudent(int studentId) {
		logger.info("Delete student with student id {}", studentId);
		try {
			studentServices.deleteStudent(studentId);
			return "Deleted Student";
		} catch (Exception e) {
			throw new ResourceNotFoundException(e.getMessage());
		}
	}

//? Dealing with PaymentServices

	@Override
	public Payment addPayment(Payment payment) {
		logger.info("Add a payment");
		return paymentServices.addPayment(payment);
	}

	@Override
	public List<Payment> getAllPendingPayments(int studentId) {
		logger.info("Get all pending payments for student id {}", studentId);
		return paymentServices.getAllPendingPayments(studentId);
	}

//? Deal with LeaveService

	@Override
	public Leave getLeave(int leaveId) {
		logger.info("Get leave with leave id {}", leaveId);
		Leave leave = leaveServices.getLeave(leaveId);
		if (leave == null) {
			throw new ResourceNotFoundException("Leave id does not exist");
		}
		return leave;
	}

	@Override
	public String deleteLeave(int leaveId) {
		logger.info("Delete a leave with leave id {}", leaveId);
		String res = leaveServices.deleteLeave(leaveId);
		if (res == null) {
			throw new ResourceNotFoundException("Leave id does not exist");
		}
		return res;
	}

	@Override
	public Leave updateLeave(Leave leave) {
		logger.info("Update leave with leave id {} to status {}", leave.getLeaveId(), leave.getStatus());
		Leave updated = leaveServices.updateLeave(leave);
		if (updated == null) {
			throw new ResourceNotFoundException("Something went wrong");
		}
		return updated;
	}

	@Override
	public List<Payment> getAllPayments() {
		logger.info("Get all payments");
		return paymentServices.getAllPayments();
	}
}
