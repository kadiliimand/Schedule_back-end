package com.example.demo.errorHandling;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ScheduleErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ScheduleException.class)
    public ResponseEntity<ResponseError> handleBankException (ScheduleException s){
        ResponseError error = new ResponseError();
        error.setMessage(s.getMessage());
        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseError> handleException (Exception e){
        e.printStackTrace();
        ResponseError error = new ResponseError();
        error.setMessage("Internal server error!");
        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @ExceptionHandler(ScheduleException.class)
//    public ResponseEntity<Object> handleBankException(ScheduleException e) {
//        ResponseError error = new ResponseError();
//        error.setMessage(e.getMessage());
//        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}