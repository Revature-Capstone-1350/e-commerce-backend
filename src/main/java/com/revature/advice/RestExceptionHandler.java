package com.revature.advice;

import com.revature.dtos.ErrorResponse;
import com.revature.exceptions.*;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler {
    /**
     *
     * The idea here is that all exceptions are coded here in the same way.
     * Error messages are only here, not in the CustomException.class.
     * The custom exceptions are the generic response codes, and other
     * exceptions that give the same code will extend/inherit the original
     */

    @ResponseStatus(HttpStatus.BAD_REQUEST) //sets response status to 400
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleInvalidArgument(
            MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(error->
            errors.add(error.getDefaultMessage())
        );
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), errors);
    }

    // Generic 501
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    @ExceptionHandler(NotImplementedException.class)
    public ErrorResponse handleNotImplementedException(Throwable t) {
        t.printStackTrace();
        String message = "This endpoint is coming soon";
        List<String> listOfErrorMessages = new ArrayList<>();
        listOfErrorMessages.add(message);
        return new ErrorResponse(HttpStatus.NOT_IMPLEMENTED.value(),
                listOfErrorMessages);
    }

    //Generic 400
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public ErrorResponse handleBadRequestException(Throwable t) {
        t.printStackTrace();
        String message = "Invalid Input.";
        List<String> listOfErrorMessages = new ArrayList<>();
        listOfErrorMessages.add(message);
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                listOfErrorMessages
        );
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PersistanceException.class)
    public ErrorResponse handlePersistanceException(PersistanceException e) {
        e.printStackTrace();
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), Collections.singletonList(e.getMessage()));
    }

    // Generic 401
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public ErrorResponse handleUnauthorizedException(Throwable t) {
        t.printStackTrace();
        String message = "Invalid Credentials.";
        List<String> listOfErrorMessages = new ArrayList<>();
        listOfErrorMessages.add(message);
        return new ErrorResponse(HttpStatus.UNAUTHORIZED.value(),
                listOfErrorMessages);
    }

//     Specific 401
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ExpiredJwtException.class)
    public ErrorResponse handleTokenExpirationException(Throwable t) {
        t.printStackTrace();
        String message = "Login session expired. Please login again.";
        List<String> listOfErrorMessages = new ArrayList<>();
        listOfErrorMessages.add(message);
        return new ErrorResponse(HttpStatus.UNAUTHORIZED.value(),
                listOfErrorMessages);
    }
    // TODO : Which one handles token expiration?
    // Specific 401
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(TokenParseException.class)
    public ErrorResponse handleTokenParseException(Throwable t) {
        t.printStackTrace();
        String message = "Login session expired. Please login again.";
        List<String> listOfErrorMessages = new ArrayList<>();
        listOfErrorMessages.add(message);
        return new ErrorResponse(HttpStatus.UNAUTHORIZED.value(),
                listOfErrorMessages);
    }

//     Generic 403
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(ForbiddenException.class)
    public ErrorResponse handleForbiddenException(Throwable t) {
        t.printStackTrace();
        String message = "Access Denied";
        List<String> listOfErrorMessages = new ArrayList<>();
        listOfErrorMessages.add(message);
        return new ErrorResponse(HttpStatus.FORBIDDEN.value(),
                listOfErrorMessages);
    }

    // Generic 404
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ErrorResponse handleNotFoundException(Throwable t) {
        t.printStackTrace();
        String message = "Resource not found.";
        List<String> listOfErrorMessages = new ArrayList<>();
        listOfErrorMessages.add(message);
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                listOfErrorMessages);
    }

    // Invalid ID 404
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ErrorResponse handleNumberFormatException(Throwable t){
        t.printStackTrace();
        String message = "Invalid ID";
        List<String> listOfErrorMessages = new ArrayList<>();
        listOfErrorMessages.add(message);
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                listOfErrorMessages);
    }

    // Generic 409
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ConflictException.class)
    public ErrorResponse handleConflictException(Throwable t) {
        t.printStackTrace();
        String message = "There is already a resource with those specifications.";
        List<String> listOfErrorMessages = new ArrayList<>();
        listOfErrorMessages.add(message);
        return new ErrorResponse(HttpStatus.CONFLICT.value(),
                listOfErrorMessages);
    }

    // Generic 500
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResponse handleOtherException(Throwable t) {
        t.printStackTrace();
        String message = "An internal server error occurred.";
        List<String> listOfErrorMessages = new ArrayList<>();
        listOfErrorMessages.add(message);
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                listOfErrorMessages);
    }
}
