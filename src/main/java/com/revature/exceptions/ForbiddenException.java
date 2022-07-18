package com.revature.exceptions;

// HTTP response code 403
public class ForbiddenException extends RuntimeException {
    public ForbiddenException() { super(); }
    public ForbiddenException(Throwable cause) { super(cause); }
}
