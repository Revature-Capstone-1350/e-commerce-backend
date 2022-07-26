package com.revature.exceptions;

public class PersistanceException extends RuntimeException{

    public PersistanceException() {
    }

    public PersistanceException(String message) {
        super(message);
    }
}
