package com.appointmentScheduling.AOP;

import com.appointmentScheduling.Exception.PatientAlreadyExistsException;
import com.appointmentScheduling.Exception.PatientException;
import com.appointmentScheduling.Exception.PatientNotFoundException;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

import java.nio.file.ProviderNotFoundException;

public class ExceptionLoggingAspect {

    static Logger log = Logger.getLogger("com.example.springProject.Aop.ExceptionLoggingAspect");

    @Around("execution(* com.example.springProject.Controller.*.*(..))")
    public Object logException(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (PatientException e) {
            log.error("PatientException in method: " + joinPoint.getSignature() + ", message: " + e.getMessage());
            throw e;
        } catch (PatientNotFoundException e) {
            log.error("PatientNotFoundException in method: " + joinPoint.getSignature() + ", message: " + e.getMessage());
            throw e;
        } catch (PatientAlreadyExistsException e) {
            log.error("PatientAlreadyExistsException in method: " + joinPoint.getSignature() + ", message: " + e.getMessage());
            throw e;
        } catch (Throwable e) {
            log.error("Unexpected error in method: " + joinPoint.getSignature() + ", message: " + e.getMessage());
            throw e;
        }
    }
}

