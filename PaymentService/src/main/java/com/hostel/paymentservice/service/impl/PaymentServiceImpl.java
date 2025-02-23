package com.hostel.paymentservice.service.impl;

import com.hostel.paymentservice.entity.Payment;
import com.hostel.paymentservice.repository.PaymentRepository;
import com.hostel.paymentservice.service.PaymentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    /**
     * Saves an object by taking a parameter as Object of .
     *
     * @param int leaveId
     * @return status as true or false.
     */
    @Override
    public Payment addPayment(Payment payment) {
    	logger.info("Added payment");
        return paymentRepository.save(payment);
    }

    //? Logic to get all payments (used by warden)
    /**
     * Deletes a already saved Leave object based on leaveId.
     *
     * @param int leaveId
     * @return status as true or false.
     */
    @Override
    public List<Payment> getAllPayments() {
    	logger.info("Get all payments");
        return paymentRepository.findAll();
    }

    //? Logic to update a payment
    /**
     * Deletes a already saved Leave object based on leaveId.
     *
     * @param int leaveId
     * @return status as true or false.
     */
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
        logger.info("updated payment {}",oldPayment);
        return paymentRepository.save(oldPayment);
    }

    //? Logic to get All payments by a student
    /**
     * Deletes a already saved Leave object based on leaveId.
     *
     * @param int leaveId
     * @return status as true or false.
     */
    @Override
    public List<Payment> getAllPaymentsByStudentId(int studentId) {
    	logger.info("Get all payments for student id {}",studentId);
    	List<Payment> payments = paymentRepository.findByStudentId(studentId);
    	logger.info("Payments by studentId {}",payments);
        return payments;
    }

    //? Logic to get a payment based on paymentID
    /**
     * Deletes a already saved Leave object based on leaveId.
     *
     * @param int leaveId
     * @return status as true or false.
     */
    @Override
    public Payment getPayment(int payId) {
    	logger.info("Get payment with payment id {}",payId);
    	return paymentRepository.findById(payId).orElse(null);
    }
}