package com.hostel.paymentservice.controller;

import com.hostel.paymentservice.entity.Payment;
import com.hostel.paymentservice.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/payment")

public class PaymentController {

    //? Inject the dependency for Payment Service
    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
    	this.paymentService = paymentService;
    }
    
    //? Add payment with status pending
    @PostMapping("/addPayment")
    public Payment addPayment(@RequestBody Payment payment) {
        payment.setPayStatus(false);
        return paymentService.addPayment(payment);
    }

    //? Get payment using payment id
    @GetMapping("/get/{payId}")
    public Payment getPayment(@PathVariable int payId){
        return paymentService.getPayment(payId);
    }

    //? Get All the pending leaves for a specific student
    @GetMapping("/getAllPendingPayments/{id}")
    public List<Payment> getAllPendingPayments(@PathVariable("id") int studentId) {

        List<Payment> payments = paymentService.getAllPaymentsByStudentId(studentId);
        List<Payment> pendingPaymentList = new ArrayList<>();
        for (Payment payment : payments) {
            if (Boolean.FALSE.equals(payment.getPayStatus())) {
                pendingPaymentList.add(payment);
            }
        }
        return pendingPaymentList;
    }

    //? Get All the leaves
    @GetMapping("/getAllPayments")
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    //? Get all the leaves for student Id
    @GetMapping("/getAllPaymentsByStudentId/{id}")
    public List<Payment> getAllPaymentsByStudentId(@PathVariable("id") int studentId) {
        return paymentService.getAllPaymentsByStudentId(studentId);
    }

    //? Update the payment (Student can update the challan No. and payment date)
    @PutMapping("/update")
    public ResponseEntity<Payment> updatePayment(@RequestBody Payment payment) {
        Payment updatedPayment = paymentService.updatePayment(payment);
        return ResponseEntity.ok(updatedPayment);
    }

}
