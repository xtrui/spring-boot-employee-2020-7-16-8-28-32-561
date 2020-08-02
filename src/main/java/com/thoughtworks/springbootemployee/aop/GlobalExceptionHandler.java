package com.thoughtworks.springbootemployee.aop;

import com.thoughtworks.springbootemployee.exception.NotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = NotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notExistExceptionHandler(RuntimeException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String illegalArgumentExceptionHandler(RuntimeException exception) {
        return exception.getMessage();
    }
}
