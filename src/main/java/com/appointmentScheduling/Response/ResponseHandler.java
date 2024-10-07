package com.appointmentScheduling.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseHandler {

    public static ResponseEntity<Object> getResponse(String responseMsg, HttpStatus status, Object responseObject) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("Response", responseMsg);
        map.put("Status", status);
        map.put("Data", responseObject);

        return new ResponseEntity<>(map, status);

    }
}
