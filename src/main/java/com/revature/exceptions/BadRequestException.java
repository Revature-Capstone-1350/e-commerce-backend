package com.revature.exceptions;

public class BadRequestException extends RuntimeException {
    public BadRequestException() {
        super();
    }
    public BadRequestException(Throwable cause) { super(cause); }
}
