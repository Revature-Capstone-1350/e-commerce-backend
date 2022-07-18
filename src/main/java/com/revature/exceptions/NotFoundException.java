package com.revature.exceptions;

// HTTP response code 404
public class NotFoundException extends RuntimeException {
    public NotFoundException() { super(); }
    public NotFoundException(Throwable cause) { super(cause); }
}
