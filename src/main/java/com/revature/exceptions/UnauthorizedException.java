package com.revature.exceptions;

// HTTP response code 401
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException() { super(); }
    public UnauthorizedException(Throwable cause) { super(cause); }
}
