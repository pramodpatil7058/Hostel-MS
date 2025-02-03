package com.hostel.paymentservice.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectAfterReturningLogger {
    private final Logger logger = LoggerFactory.getLogger(AspectAfterReturningLogger.class);

    @AfterReturning("* execution(com.hostel.paymentservice.**(..))")
    public void beforeAllPaymentController(JoinPoint joinPoint){
        logger.info("AfterReturningLogger {}",joinPoint.getSignature().getName());
    }

   
}
