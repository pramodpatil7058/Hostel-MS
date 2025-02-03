package com.hostel.paymentservice.service;

import com.hostel.paymentservice.entity.Payment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaymentService {
    Payment addPayment( Payment payment);
    List<Payment> getAllPayments();
    Payment updatePayment(Payment payment);
    List<Payment> getAllPaymentsByStudentId(int studentId);

    Payment getPayment(int payId);
}
