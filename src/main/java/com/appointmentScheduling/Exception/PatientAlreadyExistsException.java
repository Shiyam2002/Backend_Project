package com.appointmentScheduling.Exception;

public class PatientAlreadyExistsException extends Exception {

    public PatientAlreadyExistsException() {
        super();
    }

    public PatientAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public PatientAlreadyExistsException(String message) {
        super(message);
    }

    public PatientAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
