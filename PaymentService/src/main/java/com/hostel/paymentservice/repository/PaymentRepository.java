package com.hostel.paymentservice.repository;

import com.hostel.paymentservice.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    List<Payment> findByStudentId(int studentId);
    List<Payment> findPaymentByTransactionId(String transactionId);
}
