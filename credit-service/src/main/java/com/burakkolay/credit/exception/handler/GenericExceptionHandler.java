package com.burakkolay.credit.exception.handler;


import com.burakkolay.credit.exception.ApplicantNotFoundException;
import com.burakkolay.credit.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GenericExceptionHandler {

    @ExceptionHandler(ApplicantNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleApplicantNotFoundException(ApplicantNotFoundException e){
        Map<String,String> errorResponseMap = new HashMap<>();
        errorResponseMap.put("error_message",e.getMessage());
        errorResponseMap.put("error_cause",e.getCause().toString());

        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseMap);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleEntityNotFoundException(EntityNotFoundException exception) {
        Map<String, String> errorResponseMap = new HashMap<>();
        errorResponseMap.put("error_message", exception.getMessage());
        errorResponseMap.put("error_details", exception.getDetails());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseMap);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleEntityNotFoundException(Exception exception) {
        Map<String, String> errorResponseMap = new HashMap<>();
        errorResponseMap.put("error_message", exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponseMap);
    }
}
