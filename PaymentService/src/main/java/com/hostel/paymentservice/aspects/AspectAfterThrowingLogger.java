package com.hostel.paymentservice.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectAfterThrowingLogger {
    private final Logger logger = LoggerFactory.getLogger(AspectAfterThrowingLogger.class);

    @AfterThrowing("* execution(com.hostel.paymentservice.**(..))")
    public void beforeAllPaymentController(JoinPoint joinPoint, Throwable e){
        logger.info("Method {} throws {}",joinPoint.getSignature().getName(),e.getMessage());
    }

//    @AfterThrowing("* execution(com.hostel.paymentservice.service.impl.PaymentServiceImpl.*(..)")
//    public void beforeAllPaymentServiceImpl(JoinPoint joinPoint,Throwable e){
//        logger.info("Method {} throws {}",joinPoint.getSignature().getName(),e.getMessage());
//    }
}
