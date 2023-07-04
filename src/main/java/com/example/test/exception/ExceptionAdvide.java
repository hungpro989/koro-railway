package com.example.test.exception;

import com.example.test.dto.ResponseObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionAdvide {
    @ExceptionHandler
    public ResponseEntity<?> handleException(Exception ex){
        log.error("Unknown internal server error: "+ex.getMessage());
        log.error("Exception class: "+ex.getClass());
        log.error("Exception cause: "+ex.getCause());
        return new ResponseEntity<>(new ResponseObject("500", ex.getMessage(),ex.getCause()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler
    public ResponseEntity<?> handleCommonException(CommonException ex){
        log.error(String.format("Common error: %s %s %s", ex.getCode(), ex.getStatus(),ex.getMessage()));
        return new ResponseEntity<>(new ResponseObject(ex.getCode(), ex.getMessage(),ex.getCause()),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<?> handleValidateException(ValidateException ex){
        return new ResponseEntity<>(new ResponseObject("400", "Validate input",ex.getMessageMap()),HttpStatus.BAD_REQUEST);

    }
}
