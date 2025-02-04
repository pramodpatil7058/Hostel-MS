package com.hostel.studentservice.service.impl;

import com.hostel.studentservice.StudentMapper;
import com.hostel.studentservice.dto.StudentDTO;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    
    private PasswordEncoder passwordEncoder;
    
    public StudentServiceImpl(StudentRepository studentRepository, PaymentServices paymentServices, LeaveServices leaveServices, PasswordEncoder passwordEncoder) {
    	this.paymentServices = paymentServices;
    	this.leaveServices = leaveServices;
    	this.studentRepository = studentRepository;
    	this.passwordEncoder = passwordEncoder;
    }
    
    //! Logger for StudentServiceImpl class
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //? Logic for Saving a student
    @Override
    public StudentDTO saveStudent(Student student) {
    	logger.info("Save student");
    	student.setPassword(passwordEncoder.encode(student.getPassword()));
    	Student savedStudent = studentRepository.save(student);
    	StudentDTO dto = StudentMapper.mapToStudentDTO(savedStudent);
        return dto;
    }

    //? Logic for getting a student based on studentId
    @Override
    public StudentDTO getStudentById(int studentId) {
    	logger.info("Get student with student id {}",studentId);
        Student student = studentRepository.findById(studentId).orElse(null);
        if(student == null){
            throw new NoResourceFoundException("Student Id Does not exist");
        }
        ArrayList<Payment> payments = paymentServices.getPayments(studentId);
        student.setPayments(payments);
        StudentDTO dto = StudentMapper.mapToStudentDTO(student);
        return dto;
    }

    //? Logic to get All students
    @Override
    public List<StudentDTO> getAllStudents() {
    	logger.info("Get All students");
    	List<Student> students =studentRepository.findAll();
    	List<StudentDTO> studentDTOs = students.stream().map(student -> StudentMapper.mapToStudentDTO(student)).toList();
        return studentDTOs;
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
    public StudentDTO updateStudent(Student student) {
    	logger.info("Update student with student id {}",student.getStudentId());
        Student student1 = studentRepository.findById(student.getStudentId()).orElse(null);
        if(student1 == null) {
            return null;
        }
        if(student.getPassword()==null) {
        	student.setPassword(student1.getPassword());
        }
        student1 = student;
        Student updatedStudent = studentRepository.save(student1);
        return StudentMapper.mapToStudentDTO(updatedStudent);
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
    	Leave leave = leaveServices.getLeave(leaveId);
    	if(leave == null) {
    		throw new NoResourceFoundException("Leave not found");
    	}
        return leave;
    }

    //? Logic for deleting leave from studentservice to LeaveService
    @Override
    public String deleteLeave(int leaveId) {
    	logger.info("Delete leave with leave id {} from student service",leaveId);
    	String res = leaveServices.deleteLeave(leaveId);
    	if(res == null) {
    		throw new NoResourceFoundException("Leave id does not exist");
    	}
        return res;
    }

    //? Logic for updating leave from studentservice to LeaveService
    @Override
    public Leave updateLeave(Leave leave) {
    	logger.info("Update leave with leave id {} from student service",leave.getLeaveId());
    	try {
        return leaveServices.updateLeave(leave);
    	}catch(RuntimeException e) {
    		throw new NoResourceFoundException(e.getMessage());
    	}
    }
    
	@Override
	public List<Leave> getLeavesByStudentId(int studentId) {
		logger.info("Get all leaves of studentId {} from student service",studentId);
		Student student = studentRepository.findById(studentId).orElse(null);
		if(student == null) {
			throw new NoResourceFoundException("Student Id does not exist");
		}
		return leaveServices.getLeavesByStudentId(studentId);
	}
    //?Payment Services


    //?Logic to get payment from paymentService to StudentService
    @Override
    public Payment getPayment(int payId) {
    	logger.info("Get payment with payment id {}",payId);
    	Payment payment= paymentServices.getPaymentByPayId(payId);
    	if(payment == null) {
    		throw new NoResourceFoundException("Payment id does not exist");
    	}
        return payment;
    }

    //? Logic for updating Payment from studentService to paymentService
    @Override
    public Payment updatePayment(Payment payment) {
    	logger.info("Update the payment with payment id {}", payment.getPayId());
    	Payment updated =  paymentServices.updatePayment(payment);
    	if(updated == null) {
    		throw new NoResourceFoundException("Something went wrong please try again later");
    	}
        return updated;
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
