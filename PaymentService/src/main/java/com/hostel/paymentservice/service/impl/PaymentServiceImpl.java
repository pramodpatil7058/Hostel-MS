package com.hostel.paymentservice.service.impl;

import com.hostel.paymentservice.entity.Payment;
import com.hostel.paymentservice.repository.PaymentRepository;
import com.hostel.paymentservice.service.PaymentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    //? Inject the PaymentRepository dependency
    private PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
    	this.paymentRepository = paymentRepository;
    }
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    //? Logic to add a payment
    @Override
    public Payment addPayment(Payment payment) {
    	logger.info("Added payment");
        return paymentRepository.save(payment);
    }

    //? Logic to get all payments (used by warden)
    @Override
    public List<Payment> getAllPayments() {
    	logger.info("Get all payments");
        return paymentRepository.findAll();
    }

    //? Logic to update a payment
    @Override
    public Payment updatePayment(Payment payment) {
    	logger.info("Updated payment with payment Id {}",payment.getPayId());
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
    	logger.info("Get all payments for student id {}",studentId);
        return paymentRepository.findByStudentId(studentId);
    }

    //? Logic to get a payment based on paymentID
    @Override
    public Payment getPayment(int payId) {
    	logger.info("Get payment with payment id {}",payId);
        return paymentRepository.findById(payId).orElse(null);
    }
}