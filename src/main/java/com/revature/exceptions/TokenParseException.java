package com.revature.exceptions;

public class TokenParseException extends UnauthorizedException {
    public TokenParseException() { super(); }
    public TokenParseException(Throwable cause) { super(cause); }
}
