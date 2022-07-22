package com.revature.exceptions;

// HTTP response code 500
public class InternalServerErrorException extends RuntimeException {
    public InternalServerErrorException() {
        super();
    }
    public InternalServerErrorException(Throwable cause) { super(cause); }
}
