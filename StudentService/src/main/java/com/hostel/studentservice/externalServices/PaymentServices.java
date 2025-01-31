package com.hostel.studentservice.externalServices;

import com.hostel.studentservice.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@FeignClient(name = "PAYMENTSERVICE")
public interface PaymentServices {
    @GetMapping("/payment/getAllPaymentsByStudentId/{studentId}")
    ArrayList<Payment> getPayments(@PathVariable("studentId") int studentId);
}
