package com.sahil.SplitwiseApp.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggerAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggerAspect.class);

    @Before("execution(* com.sahil.SplitwiseApp.service..*(..))")
    public void methodExecutionStart(JoinPoint joinPoint) {
        logger.debug("Entry : {} method execution started.", joinPoint.getSignature().getName());
    }

    @AfterReturning("execution(* com.sahil.SplitwiseApp.service..*(..))")
    public void methodBeforeReturning(JoinPoint joinPoint){
        logger.info("Exit : {} method executed.",joinPoint.getSignature().getName());
    }

    @After("execution(* com.sahil.SplitwiseApp.validation..*(..))")
    public void validationMethodsExecuted(JoinPoint joinPoint){
        logger.info("Validation Method Executed : {}.",joinPoint.getSignature().getName());
    }

    @AfterThrowing("execution(* com.sahil.SplitwiseApp.validation..*(..))")
    public void validationFailed(JoinPoint joinPoint){
        logger.info("{} : Validation failed",joinPoint.getSignature().getName());
    }
}
