package com.hostel.studentservice.service.impl;

import com.hostel.studentservice.entities.Leave;
import com.hostel.studentservice.entities.Payment;
import com.hostel.studentservice.entities.Student;
import com.hostel.studentservice.exception.NoResourceFoundException;
import com.hostel.studentservice.external_services.LeaveServices;
import com.hostel.studentservice.external_services.PaymentServices;
import com.hostel.studentservice.repository.StudentRepository;
import com.hostel.studentservice.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pramod Patil
 * @version 1.0.0
 * @since 2025
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
    
    //! Logger for StudentServiceImpl class
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //? Logic for Saving a student
    @Override
    public Student saveStudent(Student student) {
    	logger.info("Save student");
        return studentRepository.save(student);
    }

    //? Logic for getting a student based on studentId
    @Override
    public Student getStudentById(int studentId) {
    	logger.info("Get student with student id {}",studentId);
        Student student = studentRepository.findById(studentId).orElse(null);
        if(student == null){
            throw new NoResourceFoundException("Student Id Does not exist");
        }
        ArrayList<Payment> payments = paymentServices.getPayments(studentId);
        student.setPayments(payments);
        return student;
    }

    //? Logic to get All students
    @Override
    public List<Student> getAllStudents() {
    	logger.info("Get All students");
        return studentRepository.findAll();
    }

    //? Logic for deleting a student
    @Override
    public boolean deleteStudent(int studentId) {
    	logger.info("Delete student with student id {}",studentId);
        boolean flag = false;
        Student student = studentRepository.findById(studentId).orElse(null);
        //! Check if student is not found
        if(student == null){
            throw new NoResourceFoundException("Student does not exist");
        }
            studentRepository.deleteById(studentId);
            flag = true;

        return flag;
    }

    //? Logic for updating a student
    @Override
    public Student updateStudent(Student student) {
    	logger.info("Update student with student id {}",student.getStudentId());
        Student student1 = studentRepository.findById(student.getStudentId()).orElse(null);
        if(student1 == null) {
            return null;
        }
        student1 = student;
        return studentRepository.save(student1);
    }


    //? Logic for applying leave from studentservice to LeaveService
    @Override
    public Leave applyLeave(Leave leave) {
    	logger.info("Apply leave in student Service");
        return leaveServices.applyLeave(leave);
    }

    //? Logic for getting leave from studentservice to LeaveService
    @Override
    public Leave getLeave(int leaveId) {
    	logger.info("Get leave with leave id {} from student service",leaveId);
        return leaveServices.getLeave(leaveId);
    }

    //? Logic for deleting leave from studentservice to LeaveService
    @Override
    public String deleteLeave(int leaveId) {
    	logger.info("Delete leave with leave id {} from student service",leaveId);
        return leaveServices.deleteLeave(leaveId);
    }

    //? Logic for updating leave from studentservice to LeaveService
    @Override
    public Leave updateLeave(Leave leave) {
    	logger.info("Update leave with leave id {} from student service",leave.getLeaveId());
        return leaveServices.updateLeave(leave);
    }
    
	@Override
	public List<Leave> getLeavesByStudentId(int studentId) {
		logger.info("Get all leaves of studentId {} from student service",studentId);
		return leaveServices.getLeavesByStudentId(studentId);
	}
    //?Payment Services


    //?Logic to get payment from paymentService to StudentService
    @Override
    public Payment getPayment(int payId) {
    	logger.info("Get payment with payment id {}",payId);
        return paymentServices.getPaymentByPayId(payId);
    }

    //? Logic for updating Payment from studentService to paymentService
    @Override
    public Payment updatePayment(Payment payment) {
    	logger.info("Update the payment with payment id {}", payment.getPayId());
        return paymentServices.updatePayment(payment);
    }

	@Override
	public List<Payment> getPayments(int studentId) {
		logger.info("Get all payments for student id {}",studentId);
		Student student = studentRepository.findById(studentId).orElse(null);
		if(student == null) {
			throw new NoResourceFoundException("Student Id does not exist");
		}
		return paymentServices.getPayments(studentId);
	}


}
