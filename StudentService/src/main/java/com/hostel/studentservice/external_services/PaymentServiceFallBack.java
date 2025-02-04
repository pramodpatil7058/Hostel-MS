package com.hostel.studentservice.external_services;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.hostel.studentservice.entities.Payment;
import com.hostel.studentservice.exception.NoResourceFoundException;

@Component
public class PaymentServiceFallBack implements PaymentServices {

	@Override
	public ArrayList<Payment> getPayments(int studentId) {
		throw new NoResourceFoundException("Payments for studentId not found");
	}

	@Override
	public Payment getPaymentByPayId(int payId) {
		throw new NoResourceFoundException("Invalid Payment");
	}

	@Override
	public Payment updatePayment(Payment payment) {
		throw new NoResourceFoundException("Payment update failed please try again.");
	}

}
