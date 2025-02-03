package com.hostel.paymentservice.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectAfterLogger {

    private final Logger logger = LoggerFactory.getLogger(AspectAfterLogger.class);

    @After("* execution(com.hostel.paymentservice.**(..))")
    public void logAfterAllPaymentController(JoinPoint joinPoint) {
        logger.info("After {}", joinPoint.getSignature().getName());
    }

}
