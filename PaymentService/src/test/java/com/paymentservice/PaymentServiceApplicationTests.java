package com.paymentservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.hostel.paymentservice.PaymentServiceApplication;
import com.hostel.paymentservice.entity.Payment;
import com.hostel.paymentservice.repository.PaymentRepository;
import com.hostel.paymentservice.service.impl.PaymentServiceImpl;

@SpringBootTest(classes = PaymentServiceApplication.class)
class PaymentServiceApplicationTests {

	@Mock
	PaymentRepository paymentRepository;

	@InjectMocks
	PaymentServiceImpl paymentServiceImpl;

	List<Payment> payments = new ArrayList<>();

	Payment payment;

	@BeforeEach
	public void setup() {

		payment = new Payment(2, 12000, 1, "13-12-2024", "Fees", "C-1235", false);

		payments.add(new Payment(1, 2000, 1, "12-12-2024", "Fine", "C-1234", true));
		payments.add(new Payment(2, 12000, 2, "13-12-2024", "Fees", "C-1235", false));
		payments.add(new Payment(3, 20000, 1, "14-12-2024", "Fees", "C-1236", true));
		payments.add(new Payment(4, 2500, 2, "15-12-2024", "Fine", "C-1237", false));
		payments.add(new Payment(5, 500, 1, "16-12-2024", "Fine", "C-1238", true));
	}

	@Test
	void testGetAllPayments() {
		when(paymentRepository.findAll()).thenReturn(payments);
		assertEquals(5, paymentServiceImpl.getAllPayments().size());
	}

	@Test
	void testGetPaymentById_ValidId() {
		int validId = 2;
		when(paymentRepository.findById(validId)).thenReturn(Optional.of(payment));
		assertEquals(payment, paymentServiceImpl.getPayment(validId));
	}

	@Test
	void testGetPaymentById_Invalid() {
		int invalidId = 12355;
		when(paymentRepository.findById(invalidId)).thenReturn(Optional.empty());
		assertEquals(null, paymentServiceImpl.getPayment(invalidId));
	}

	@Test
	void testGetPaymentByStudentId() {
		int studentId = 2;
		when(paymentRepository.findByStudentId(studentId)).thenReturn(List.of(payments.get(1), payments.get(3)));
		assertEquals(2, paymentServiceImpl.getAllPaymentsByStudentId(studentId).size());
	}

}
