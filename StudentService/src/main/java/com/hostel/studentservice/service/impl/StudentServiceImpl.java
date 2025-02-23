package com.hostel.studentservice.service.impl;

import com.hostel.studentservice.mapper.StudentMapper;
import com.hostel.studentservice.dto.StudentDTO;
import com.hostel.studentservice.entities.Leave;
import com.hostel.studentservice.entities.Payment;
import com.hostel.studentservice.entities.Student;
import com.hostel.studentservice.exception.ResourceNotFoundException;
import com.hostel.studentservice.external_services.LeaveServices;
import com.hostel.studentservice.external_services.PaymentServices;
import com.hostel.studentservice.repository.StudentRepository;
import com.hostel.studentservice.service.StudentService;


import org.springframework.security.crypto.password.PasswordEncoder;
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
    
    private PasswordEncoder passwordEncoder;
    
    public StudentServiceImpl(StudentRepository studentRepository, PaymentServices paymentServices, LeaveServices leaveServices, PasswordEncoder passwordEncoder) {
    	this.paymentServices = paymentServices;
    	this.leaveServices = leaveServices;
    	this.studentRepository = studentRepository;
    	this.passwordEncoder = passwordEncoder;
    }
    

    //? Logic for Saving a student
    @Override
    public StudentDTO saveStudent(Student student) {
    	student.setPassword(passwordEncoder.encode(student.getPassword()));
    	Student savedStudent = studentRepository.save(student);

    	return StudentMapper.mapToStudentDTO(savedStudent);
    }

    //? Logic for getting a student based on studentId
    @Override
    public StudentDTO getStudentById(int studentId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        System.out.println(student);
        if(student == null) {
        	throw new ResourceNotFoundException("Student Id does not exist");
        }
        return StudentMapper.mapToStudentDTO(student);
    }

    //? Logic to get All students
    @Override
    public List<StudentDTO> getAllStudents() {
    	List<Student> students =studentRepository.findAll();
    	return students.stream().map(StudentMapper::mapToStudentDTO).toList();
    }

    //? Logic for deleting a student
    @Override
    public boolean deleteStudent(int studentId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        //! Check if student is not found
        if(student == null) {
        	throw new ResourceNotFoundException("Student ID does not exist.");
        }
        studentRepository.deleteById(studentId);
        return true;
    }

    //? Logic for updating a student
    @Override
    public StudentDTO updateStudent(Student student) {
        Student student1 = studentRepository.findById(student.getStudentId()).orElse(null);
        if(student1 == null) {
        	throw new ResourceNotFoundException("Student ID does not exist.");
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
        	throw new ResourceNotFoundException("Student ID does not exist.");
        }
    	return payment;
    	
    }

    //? Logic for updating Payment from studentService to paymentService
    @Override
    public Payment updatePayment(Payment payment) {
    	Payment updated = paymentServices.updatePayment(payment);
    	if(updated == null) {
        	throw new ResourceNotFoundException("Student ID does not exist.");
        }
    	return updated;
    }

	@Override
	public List<Payment> getPayments(int studentId) {
		Student student = studentRepository.findById(studentId).orElse(null);
		if(student == null) {
        	throw new ResourceNotFoundException("Student ID does not exist.");
        }
		return paymentServices.getPayments(studentId);
	}


	@Override
	public List<Payment> getPendingPayments(int studentId) {
		Student student = studentRepository.findById(studentId).orElse(null);
		if(student == null) {
        	throw new ResourceNotFoundException("Student ID does not exist.");
        }
		return paymentServices.getAllPendingPaymentsByStudentId(studentId);
	}

    @Override
    public StudentDTO login(Student student) {
        Student savedStudent = studentRepository.findStudentByEmail(student.getEmail());
        if(savedStudent == null ||savedStudent.getStudentName() == null){
            throw new ResourceNotFoundException("Student Does not exist");
        }
        return StudentMapper.mapToStudentDTO(savedStudent);
    }


}
