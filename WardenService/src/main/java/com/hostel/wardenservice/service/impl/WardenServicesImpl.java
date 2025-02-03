package com.hostel.wardenservice.service.impl;

import com.hostel.wardenservice.entity.Leave;
import com.hostel.wardenservice.entity.Payment;
import com.hostel.wardenservice.entity.Student;
import com.hostel.wardenservice.exception.ResourceNotFoundException;
import com.hostel.wardenservice.external_services.LeaveServices;
import com.hostel.wardenservice.external_services.PaymentServices;
import com.hostel.wardenservice.external_services.StudentServices;
import com.hostel.wardenservice.service.WardenServices;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WardenServicesImpl implements WardenServices {

    private StudentServices studentServices;

    private PaymentServices paymentServices;

    private LeaveServices leaveServices;

    public WardenServicesImpl(StudentServices studentServices, PaymentServices paymentServices, LeaveServices leaveServices) {
    	this.leaveServices = leaveServices;
    	this.paymentServices = paymentServices;
    	this.studentServices = studentServices;
    }
    
    //? Dealing with StudentService
    @Override
    public Student changeApplicationStatus(int studentId, boolean status) {
        try {
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
        return paymentServices.addPayment(payment);
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
