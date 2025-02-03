package com.hostel.studentservice.external_services;

import com.hostel.studentservice.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.ArrayList;

@FeignClient(name = "PAYMENTSERVICE")
public interface PaymentServices {
    @GetMapping("/payment/getAllPaymentsByStudentId/{studentId}")
    ArrayList<Payment> getPayments(@PathVariable("studentId") int studentId);

    @GetMapping("/payment/get/{payId}")
    Payment getPaymentByPayId(@PathVariable int payId);

    @PutMapping("/payment/update")
    Payment updatePayment(Payment payment);
}
