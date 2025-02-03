package com.hostel.paymentservice.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class AspectBeforeLogger {

    private final Logger logger = LoggerFactory.getLogger(AspectBeforeLogger.class);

    @Before("execution(* com.hostel.paymentservice.controller.PaymentController.*(..))")
    public void logBeforeAllPaymentController(JoinPoint joinPoint){
        logger.info("Before {}",joinPoint.getSignature().getName());
    }


}
