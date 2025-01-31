package com.hostel.paymentservice.controller;

import com.hostel.paymentservice.entity.Payment;
import com.hostel.paymentservice.exception.ResourceNotFoundException;
import com.hostel.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/addPayment")
    public Payment addPayment(@RequestBody Payment payment){
        payment.setPayStatus(false);
        return paymentService.addPayment(payment);
    }

    @GetMapping("/getAllPendingPayments/{id}")
    public List<Payment> getAllPendingPayments(@PathVariable("id") int studentId){

        List<Payment> payments = paymentService.getAllPaymentsByStudentId(studentId);
        List<Payment> pendingPaymentList = new ArrayList<>();
        for(Payment payment:payments){
            if(!payment.getPayStatus()){
                pendingPaymentList.add(payment);
            }
        }
        return pendingPaymentList;
    }
    @GetMapping("/getAllPayments")
    public List<Payment> getAllPayments(){
        return paymentService.getAllPayments();
    }
    @GetMapping("/getAllPaymentsByStudentId/{id}")
    public List<Payment> getAllPaymentsByStudentId(@PathVariable("id") int studentId){
        return paymentService.getAllPaymentsByStudentId(studentId);
    }

    @PutMapping("/paymentDone")
    public ResponseEntity<Payment> updatePayment(@RequestBody Payment payment){
        Payment updatedPayment = paymentService.updatePayment(payment);
        if(updatedPayment == null){
            throw new ResourceNotFoundException("Payment Invalid");
        }
        return ResponseEntity.ok(updatedPayment);
    }

}
