package com.appointmentScheduling.Interceptors;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.stereotype.Component;

@Component
public class Logging {
    static Logger log = Logger.getLogger("com.example.springProject.Aop.LoggingAspect");

    @Around("execution(* com.example.springProject.Controller.ProviderController.*(..))")
    public Object logAfter(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();
        log.info("Executed method: " + joinPoint.getSignature().getName() + " with result: " + result);
        return result;
    }

}
