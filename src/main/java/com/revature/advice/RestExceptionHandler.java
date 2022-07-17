package com.revature.advice;

import com.revature.dtos.ErrorResponse;
import com.revature.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    @ExceptionHandler(NotImplementedException.class)
    public ErrorResponse handleNotImplementedException() {
        return new ErrorResponse(HttpStatus.NOT_IMPLEMENTED.value(),
                "This endpoint is coming soon");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ErrorResponse handleMethodArgumentNotValidExceptionException(
            MethodArgumentNotValidException e
    ) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                "Invalid Input.");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public ErrorResponse handleBadRequestException() {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                "Invalid Input.");
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public ErrorResponse handleUnauthorizedException() {
        return new ErrorResponse(HttpStatus.UNAUTHORIZED.value(),
                "Invalid Credentials.");
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(TokenParseException.class)
    public ErrorResponse handleTokenParseException() {
        return new ErrorResponse(HttpStatus.UNAUTHORIZED.value(),
                "Login session expired. Please login again.");
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ConflictException.class)
    public ErrorResponse handleConflictException() {
        return new ErrorResponse(HttpStatus.CONFLICT.value(),
                "There is already a resource with those specifications.");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ErrorResponse handleNotFoundException() {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                "Resource not found.");
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResponse handleOtherException(Throwable t) {
        t.printStackTrace();
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "An internal server error occurred.");
    }
}
