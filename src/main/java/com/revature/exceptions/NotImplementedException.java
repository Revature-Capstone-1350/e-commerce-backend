package com.revature.exceptions;

// HTTP response code 501
public class NotImplementedException extends RuntimeException {
    public NotImplementedException() { super(); }
    public NotImplementedException(Throwable cause) { super(cause); }
}

