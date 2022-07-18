package com.revature.exceptions;

// HTTP response code 401
// USed to inform the user their session has expired
public class TokenParseException extends UnauthorizedException {
    public TokenParseException() { super(); }
    public TokenParseException(Throwable cause) { super(cause); }
}
