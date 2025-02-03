package com.hostel.studentservice.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class StudentLoggerAspect {
    private final Logger logger = LoggerFactory.getLogger(StudentLoggerAspect.class);

    @Before("execution(* com.hostel.studentservice.service.impl.*.*(..)")
    public void logBeforeAllMethods(JoinPoint joinPoint){
        logger.info("This is before {}",joinPoint.getSignature().getName());
        logger.info("The arguments are: ");
        for(Object arg : joinPoint.getArgs()){
            logger.info("{}",arg);
        }
    }
}
