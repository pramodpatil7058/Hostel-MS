package com.hostel.studentservice.service.impl;

import com.hostel.studentservice.entities.Leave;
import com.hostel.studentservice.entities.Payment;
import com.hostel.studentservice.entities.Student;
import com.hostel.studentservice.externalServices.LeaveServices;
import com.hostel.studentservice.externalServices.PaymentServices;
import com.hostel.studentservice.repository.StudentRepository;
import com.hostel.studentservice.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PaymentServices paymentServices;

    @Autowired
    private LeaveServices leaveServices;

    private Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(int studentId) {
        Student student = studentRepository.findById(studentId).orElse(null);
//        ArrayList payments = restTemplate.getForObject("http://PaymentService/payment/getAllPaymentsByStudentId/" + studentId, ArrayList.class);
        ArrayList<Payment> payments = paymentServices.getPayments(studentId);
        student.setPayments(payments);
//    logger.info("{}",payments);
        return student;
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<Student> studentsWithPayments = new ArrayList<Student>();
        for(Student student : students){
            ArrayList payments = restTemplate.getForObject("http://PaymentService/payment/getAllPaymentsByStudentId/" + student.getStudentId(), ArrayList.class);
            student.setPayments(payments);
            studentsWithPayments.add(student);
        }
        return studentsWithPayments;
    }

    @Override
    public boolean deleteStudent(int studentId) {
        boolean flag = false;
        Student student = studentRepository.findById(studentId).orElse(null);
        if(student != null){
            studentRepository.deleteById(studentId);
            flag = true;
        }
        return flag;
    }

    @Override
    public Student updateStudent(Student student) {
        Student student1 = studentRepository.findById(student.getStudentId()).orElse(null);
        if(student1 == null) {
            return null;
        }
        student1 = student;
        System.out.println(student1);
        return studentRepository.save(student1);
    }

    @Override
    public Leave applyLeave(Leave leave) {
        return leaveServices.applyLeave(leave);
    }

    @Override
    public Leave getLeave(int leaveId) {
        return leaveServices.getLeave(leaveId);
    }

    @Override
    public String deleteLeave(int leaveId) {
        return leaveServices.deleteLeave(leaveId);
    }

    @Override
    public Leave updateLeave(Leave leave) {
        return leaveServices.updateLeave(leave);
    }
}
