package com.appointmentScheduling.AOP;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

public class LoggingAspect {

    static Logger log = Logger.getLogger("com.example.springProject.Aop.LoggingAspect");

    @Around("execution(* com.example.springProject.Controller.PatientController.*(..))")
    public Object logAfter(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();
        log.info("Executed method: " + joinPoint.getSignature().getName() + " with result: " + result);
        return result;
    }

}
