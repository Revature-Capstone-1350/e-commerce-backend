package com.revature.exceptions;

// HTTP response code 400
public class BadRequestException extends RuntimeException {
    public BadRequestException() {
        super();
    }
    public BadRequestException(Throwable cause) { super(cause); }
}
