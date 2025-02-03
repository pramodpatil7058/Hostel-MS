package com.hostel.wardenservice.externalServices;

import com.hostel.wardenservice.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("PAYMENTSERVICE")
public interface PaymentServices {

    @PostMapping("/payment/addPayment")
    Payment addPayment(@RequestBody Payment payment);
    @GetMapping("/payment/getAllPendingPayments/{studentId}")
    List<Payment> getAllPendingPayments(@PathVariable int studentId);
    @GetMapping("/payment/getAllPayments")
    List<Payment> getAllPayments();
}
