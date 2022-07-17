package com.revature.exceptions;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException() { super(); }
    public UnauthorizedException(Throwable cause) { super(cause); }
}
