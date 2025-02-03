package com.hostel.wardenservice.service.impl;

import com.hostel.wardenservice.entity.Leave;
import com.hostel.wardenservice.entity.Payment;
import com.hostel.wardenservice.entity.Student;
import com.hostel.wardenservice.exception.ResourceNotFoundException;
import com.hostel.wardenservice.externalServices.LeaveServices;
import com.hostel.wardenservice.externalServices.PaymentServices;
import com.hostel.wardenservice.externalServices.StudentServices;
import com.hostel.wardenservice.service.WardenServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class WardenServicesImpl implements WardenServices {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StudentServices studentServices;

    @Autowired
    private PaymentServices paymentServices;

    @Autowired
    private LeaveServices leaveServices;

    //? Dealing with StudentService
    @Override
    public Student changeApplicationStatus(int studentId, boolean status) {
        try {
            System.out.println("status "+status);
            Student student = studentServices.getStudent(studentId);
            student.setStatus(status);
            return studentServices.updateStudent(student);
        }catch(ResourceNotFoundException e){
            return null;
        }
    }

    @Override
    public List<Student> getAllStudents() {
        return studentServices.getAllStudents();
    }



    @Override
    public String removeStudent(int studentId)  {
       studentServices.deleteStudent(studentId);
        return "Deleted Student";

    }


//? Dealing with PaymentServices


    @Override
    public Payment addPayment(Payment payment) {
//        restTemplate.postForLocation("http://PaymentService/payment/addPayment",payment);
        Payment savedPayment = paymentServices.addPayment(payment);
        System.out.println(payment);
        return savedPayment;
    }


    @Override
    public List<Payment> getAllPendingPayments(int studentId) {
         return paymentServices.getAllPendingPayments(studentId);
    }


//? Deal with LeaveService


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

    @Override
    public List<Payment> getAllPayments() {
        return List.of();
    }
}
