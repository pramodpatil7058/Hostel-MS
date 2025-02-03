package com.hostel.paymentservice.service.impl;

import com.hostel.paymentservice.entity.Payment;
import com.hostel.paymentservice.repository.PaymentRepository;
import com.hostel.paymentservice.service.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    //? Inject the PaymentRepository dependancy
    private PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository) {
    	this.paymentRepository = paymentRepository;
    }
    
    //? Logic to add a payment
    @Override
    public Payment addPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    //? Logic to get all payments (used by warden)
    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    //? Logic to update a payment
    @Override
    public Payment updatePayment(Payment payment) {
        Payment oldPayment = paymentRepository.findById(payment.getPayId()).orElse(null);
        if(oldPayment == null){
            return null;
        }
        Payment tranPayment = paymentRepository.findPaymentByTransactionId(payment.getTransactionId());
        if(tranPayment !=null){
            return null;
        }
        oldPayment.setTransactionId(payment.getTransactionId());
        oldPayment.setPayDate(payment.getPayDate());
        oldPayment.setPayStatus(true);
        return paymentRepository.save(oldPayment);
    }

    //? Logic to get All payments by a student
    @Override
    public List<Payment> getAllPaymentsByStudentId(int studentId) {
        return paymentRepository.findByStudentId(studentId);
    }

    //? Logic to get a payment based on paymentID
    @Override
    public Payment getPayment(int payId) {
        return paymentRepository.findById(payId).orElse(null);
    }
}