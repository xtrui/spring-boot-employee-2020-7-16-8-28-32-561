package com.thoughtworks.springbootemployee.aop;

import com.thoughtworks.springbootemployee.exception.NotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = NotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String NotExistExceptionHandler(RuntimeException exception) {
        return exception.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String IllegalArgumentExceptionHandler(RuntimeException exception) {
        return exception.getMessage();
    }
}
