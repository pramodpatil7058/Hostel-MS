package com.hostel.paymentservice.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectAroundLogger {
    private final Logger logger = LoggerFactory.getLogger(AspectAroundLogger.class);

    @Around("* execution(com.hostel.paymentservice.**(..))")
    public void logAroundAllPaymentController(JoinPoint joinPoint){
        logger.info("Around Method {}",joinPoint.getSignature().getName());
    }
}
