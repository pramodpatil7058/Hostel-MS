package com.hostel.studentservice.service.impl;

import com.hostel.studentservice.entities.Leave;
import com.hostel.studentservice.entities.Payment;
import com.hostel.studentservice.entities.Student;
import com.hostel.studentservice.exception.PaymentException;
import com.hostel.studentservice.exception.ResourceNotFoundException;
import com.hostel.studentservice.external_services.LeaveServices;
import com.hostel.studentservice.external_services.PaymentServices;
import com.hostel.studentservice.repository.StudentRepository;
import com.hostel.studentservice.service.StudentService;


import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author Pramod Patil
 * @version 1.0.0
 * @since 2025
 * 
 * 
 */

@Service
public class StudentServiceImpl implements StudentService {

    //? Inject the dependency for StudentRepository
    private StudentRepository studentRepository;

    //? Inject a dependency for ExternalPaymentService
    private PaymentServices paymentServices;

    //? Inject a dependency for External LeaveService
    private LeaveServices leaveServices;
       
    public StudentServiceImpl(StudentRepository studentRepository, PaymentServices paymentServices, LeaveServices leaveServices) {
    	this.paymentServices = paymentServices;
    	this.leaveServices = leaveServices;
    	this.studentRepository = studentRepository;
    }
    

    //? Logic for Saving a student
    @Override
    public Student saveStudent(Student student) {
    	System.out.println("Save Student");
    	return studentRepository.save(student);
    }

    //? Logic for getting a student based on studentId
    @Override
    public Student getStudent(int id) {
    	System.out.println("Get Student");
    	Student st = studentRepository.findById(id).orElse(null);
    	System.out.println(st);
    	if(st == null) {
    		throw new ResourceNotFoundException("Student Id does not exist.");
    	}
    	return st;
    }

    //? Logic to get All students
    @Override
    public List<Student> getAllStudents() {
    	return studentRepository.findAll();
    	
    }

    //? Logic for deleting a student
    @Override
    public boolean deleteStudent(int studentId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        //! Check if student is not found
        if(student == null) {
        	throw new ResourceNotFoundException("Student ID does not exist.");
        }
        if(leaveServices.deleteAllByUserId(student.getStudentId()).equalsIgnoreCase("Success")) {
        	studentRepository.deleteById(studentId);
        };
        return true;
    }

    //? Logic for updating a student
    @Override
    public Student updateStudent(Student student) {
    	System.out.println("Update Student");
        Student student1 = studentRepository.findById(student.getStudentId()).orElse(null);
        if(student1 == null) {
        	throw new ResourceNotFoundException("Student ID does not exist.");
        }
        student1 = student;
        return studentRepository.save(student1);
    }


    //? Logic for applying leave from studentservice to LeaveService
    @Override
    public Leave applyLeave(Leave leave) {
    	Student student = studentRepository.findById(leave.getStudentId()).orElse(null);
    	if(student == null) {
        	throw new ResourceNotFoundException("Student ID does not exist.");
        }
    	Leave updated = leaveServices.applyLeave(leave);
    	if(updated == null) {
        	throw new ResourceNotFoundException("Student ID does not exist.");
        }
        return updated;
    }

    //? Logic for getting leave from studentservice to LeaveService
    @Override
    public Leave getLeave(int leaveId)  {
    	Leave leave = leaveServices.getLeave(leaveId);
    	if(leave == null) {
        	throw new ResourceNotFoundException("Student ID does not exist.");
        }

    	return leave;
    }

    //? Logic for deleting leave from studentservice to LeaveService
    @Override
    public String deleteLeave(int leaveId) {
    	String res = leaveServices.deleteLeave(leaveId);
    	if(res == null) {
        	throw new ResourceNotFoundException("Student ID does not exist.");
        }
    	return res;
    }

    //? Logic for updating leave from studentservice to LeaveService
    @Override
    public Leave updateLeave(Leave leave) {
    	Leave updated = leaveServices.updateLeave(leave);
    	if(updated == null) {
        	throw new ResourceNotFoundException("Student ID does not exist.");
        }
        return updated;
    }
    
	@Override
	public List<Leave> getLeavesByStudentId(int studentId) {
		Student student = studentRepository.findById(studentId).orElse(null);
		if(student == null) {
        	throw new ResourceNotFoundException("Student ID does not exist.");
        }
		return leaveServices.getLeavesByStudentId(studentId);
	}
    //?Payment Services


    //?Logic to get payment from paymentService to StudentService
    @Override
    public Payment getPayment(int payId) {
    	Payment payment = paymentServices.getPaymentByPayId(payId);
    	if(payment == null) {
        	throw new PaymentException("Invalid Payment");
        }
    	return payment;
    	
    }

    //? Logic for updating Payment from studentService to paymentService
    @Override
    public Payment updatePayment(Payment payment) {
    	Payment updated = paymentServices.updatePayment(payment);
    	if(updated == null) {
        	throw new PaymentException("Payment Invalid");
        }
    	return updated;
    }

	@Override
	public List<Payment> getPayments(int studentId) {
		Student student = studentRepository.findById(studentId).orElse(null);
		if(student == null) {
        	throw new PaymentException("Invalid Payment");
        }
		return paymentServices.getPayments(studentId);
	}


	@Override
	public List<Payment> getPendingPayments(int studentId) {
		Student student = studentRepository.findById(studentId).orElse(null);
		if(student == null) {
        	throw new PaymentException("Invalid Request");
        }
		return paymentServices.getAllPendingPaymentsByStudentId(studentId);
	}


	@Override
	public List<Leave> getAllLeaves() {
		return leaveServices.getAllLeaves();
	}


   

}
