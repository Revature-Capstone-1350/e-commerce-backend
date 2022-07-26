package com.revature.advice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandlerAll {

    @ExceptionHandler
    public void handleAllException(Throwable t) {
        t.printStackTrace();
    }
}
