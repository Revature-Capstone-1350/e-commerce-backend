package com.revature.exceptions;

public class NotImplementedException extends RuntimeException {

    public NotImplementedException() {
        super("This endpoint is coming soon");
    }

    public NotImplementedException(String message) {
        super(message);
    }

    public NotImplementedException(String message, Throwable cause) {
        super(message, cause);
    }
}

