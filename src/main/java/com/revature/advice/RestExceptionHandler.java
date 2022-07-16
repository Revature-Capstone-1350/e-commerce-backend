package com.revature.advice;

import com.revature.exceptions.AuthenticationException;
import com.revature.exceptions.NotImplementedException;
import com.revature.exceptions.NotLoggedInException;
import com.revature.exceptions.TokenParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(NotImplementedException.class)
    public ResponseEntity<Object> handleNotImplementedException(NotImplementedException e) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(e.getMessage());
    }


    @ExceptionHandler(NotLoggedInException.class)
    public ResponseEntity<Object> handleNotLoggedInException(NotLoggedInException e) {
        String errorMessage = "Must be logged in to perform this action";
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage);
    }

    @ExceptionHandler(TokenParseException.class)
    public ResponseEntity<Object> handleTokenParseException(TokenParseException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
    /*
    // Unauthorized registration authentication status exception handler
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler
    public ErrorResponse handleResourceNotFoundException(AuthenticationException e) {
        return new ErrorResponse(401, e.getMessage());
    }

    // Resource not found status exception handler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public ErrorResponse handleResourceNotFoundException(EntityNotFoundException e) {
        return new ErrorResponse(404, e.getMessage());
    }

    // Conflict status exception handler
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler
    public ErrorResponse handleResourcePersistenceException(ResourcePersistenceException e) {
        return new ErrorResponse(409, e.getMessage());
    }

    // Internal server error status exception handler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResponse handleOtherException(Throwable t) {
        t.printStackTrace();
        return new ErrorResponse(500, "An internal server error occurred");
    }
     */
}
