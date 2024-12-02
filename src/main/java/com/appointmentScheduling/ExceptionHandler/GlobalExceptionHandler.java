package com.appointmentScheduling.ExceptionHandler;


import com.appointmentScheduling.Exception.*;
import com.appointmentScheduling.Response.ResponseHandler;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    static Logger log = Logger.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(PatientException.class)
    public ResponseEntity<Object> handlePatientException(PatientException patientException) {
        if (log.isDebugEnabled()) {
            log.debug("PatientException : " + patientException.getMessage());
        }
        return ResponseHandler.getResponse("PatientException :" + patientException.getMessage(), HttpStatus.BAD_REQUEST, null);
    }

    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<Object> handlePatientNotFoundException(PatientNotFoundException patientNotFoundException) {
        if (log.isDebugEnabled()) {
            log.debug("PatientNotFoundException : " + patientNotFoundException.getMessage());
        }
        return ResponseHandler.getResponse("PatientNotFoundException :" + patientNotFoundException.getMessage(), HttpStatus.NOT_FOUND, null);
    }

    @ExceptionHandler(PatientAlreadyExistsException.class)
    public ResponseEntity<Object> handlePatientAlreadyExistsException(PatientAlreadyExistsException patientAlreadyExists) {
        if (log.isDebugEnabled()) {
            log.debug("Patient already exists" + patientAlreadyExists.getMessage());
        }
        return ResponseHandler.getResponse("PatientAlreadyExistsException :" + patientAlreadyExists.getMessage(), HttpStatus.BAD_REQUEST, null);
    }

    @ExceptionHandler(AppointmentNotFoundException.class)
    public ResponseEntity<Object> handleAppointmentNotFoundException(AppointmentNotFoundException appointmentNotFoundException) {
        if (log.isDebugEnabled()) {
            log.debug("Patient already exists" + appointmentNotFoundException.getMessage());
        }
        return ResponseHandler.getResponse("PatientAlreadyExistsException :" + appointmentNotFoundException.getMessage(), HttpStatus.BAD_REQUEST, null);
    }

    @ExceptionHandler(AppointmentException.class)
    public ResponseEntity<Object> handleAppointmentException(AppointmentException appointmentException) {
        if (log.isDebugEnabled()) {
            log.debug("Patient already exists" + appointmentException.getMessage());
        }
        return ResponseHandler.getResponse("PatientAlreadyExistsException :" + appointmentException.getMessage(), HttpStatus.BAD_REQUEST, null);
    }

    @ExceptionHandler(AppointmentAlreadyExistException.class)
    public ResponseEntity<Object> handleAppointmentAlreadyExistException(AppointmentAlreadyExistException AppointmentAlreadyExistException) {
        if (log.isDebugEnabled()) {
            log.debug("Patient already exists" + AppointmentAlreadyExistException.getMessage());
        }
        return ResponseHandler.getResponse("PatientAlreadyExistsException :" + AppointmentAlreadyExistException.getMessage(), HttpStatus.BAD_REQUEST, null);
    }
}
