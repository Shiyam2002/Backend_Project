package com.appointmentScheduling.Exception;

public class PatientException extends Exception {

    public PatientException() {
        super();
    }

    public PatientException(String message, Throwable cause) {
        super(message, cause);
    }

    public PatientException(String message) {
        super(message);
    }

    public PatientException(Throwable cause) {
        super(cause);
    }

}
