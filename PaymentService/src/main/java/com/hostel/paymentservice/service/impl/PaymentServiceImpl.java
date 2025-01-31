package com.hostel.paymentservice.service.impl;

import com.hostel.paymentservice.entity.Payment;
import com.hostel.paymentservice.repository.PaymentRepository;
import com.hostel.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment addPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

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

    @Override
    public List<Payment> getAllPaymentsByStudentId(int studentId) {
        return paymentRepository.findByStudentId(studentId);
    }
}