package com.hostel.studentservice.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class StudentLoggerAspect {
    private final Logger logger = LoggerFactory.getLogger(StudentLoggerAspect.class);

    @Before("execution(* com.hostel.studentservice.service.impl.*.*(..))")
    public void logBeforeAllMethods(JoinPoint joinPoint){
        logger.info("This is before {}",joinPoint.getSignature().getName());
        logger.info("The arguments are: ");
        for(Object arg : joinPoint.getArgs()){
            logger.info("{}",arg);
        }
    }
    
    @Around("execution(* com.hostel.studentservice.service.impl.*.*(..))")
    public Object logAroundMethods(ProceedingJoinPoint joinPoint) {
    	logger.info("Executing method : {}",joinPoint.getSignature().getName());
    	logger.info("Arguments : {}",joinPoint.getArgs());
    	
    	Object result = null;
    	try {
			result = joinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
    	logger.info("Executed method : {}",joinPoint.getSignature().getName());
    	logger.info("The result is : {}",result);
    	
    	return result;
    }
    
    @After("execution(* com.hostel.studentservice.service.impl.*.*(..))")
    public void logAfterAll(JoinPoint joinPoint) {
    	logger.info("Executed method : {}",joinPoint.getSignature().getName());
    }
    
    @AfterThrowing("execution(* com.hostel.studentservice.service.impl.*.*(..))")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
    	logger.info("Method {} throw {} Exception", joinPoint.getSignature().getName(), e.getMessage());
    }
}
