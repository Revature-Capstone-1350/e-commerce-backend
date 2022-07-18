package com.revature.exceptions;

// HTTP response code 409
public class ConflictException extends RuntimeException {
    public ConflictException() {
        super();
    }
    public ConflictException(Throwable cause) { super(cause); }
}
