package com.revature.advice;

import com.revature.dtos.ErrorResponse;
import com.revature.exceptions.*;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {
    /**
     *
     * The idea here is that all exceptions are coded here in the same way.
     * Error messages are only here, not in the CustomException.class.
     * The custom exceptions are the generic response codes, and other
     * exceptions that give the same code will extend/inherit the original
     */


    // Generic 501
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    @ExceptionHandler(NotImplementedException.class)
    public ErrorResponse handleNotImplementedException(Throwable t) {
        t.printStackTrace();
        return new ErrorResponse(HttpStatus.NOT_IMPLEMENTED.value(),
                "This endpoint is coming soon");
    }

    // Generic 400
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public ErrorResponse handleBadRequestException(Throwable t) {
        t.printStackTrace();
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                "Invalid Input.");
    }

    // Specific 400
    // This handles error thrown via the DTO validation annotations
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e
    ) {
        e.printStackTrace();
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                "Invalid Input."); // TODO : give message from DTO
    }

    // Generic 401
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public ErrorResponse handleUnauthorizedException(Throwable t) {
        t.printStackTrace();
        return new ErrorResponse(HttpStatus.UNAUTHORIZED.value(),
                "Invalid Credentials.");
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ExpiredJwtException.class)
    public ErrorResponse handleTokenExpirationException(Throwable t) {
        t.printStackTrace();
        return new ErrorResponse(HttpStatus.UNAUTHORIZED.value(),
                "Login session expired. Please login again.");
    }

    // Specific 401
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(TokenParseException.class)
    public ErrorResponse handleTokenParseException(Throwable t) {
        t.printStackTrace();
        return new ErrorResponse(HttpStatus.UNAUTHORIZED.value(),
                "Login session expired. Please login again.");
    }

    // Generic 403
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(ForbiddenException.class)
    public ErrorResponse handleForbiddenException(Throwable t) {
        t.printStackTrace();
        return new ErrorResponse(HttpStatus.FORBIDDEN.value(),
                "Access Denied");
    }

    // Generic 404
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ErrorResponse handleNotFoundException(Throwable t) {
        t.printStackTrace();
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                "Resource not found.");
    }

    // Generic 409
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ConflictException.class)
    public ErrorResponse handleConflictException(Throwable t) {
        t.printStackTrace();
        return new ErrorResponse(HttpStatus.CONFLICT.value(),
                "There is already a resource with those specifications.");
    }

    // Generic 500
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResponse handleOtherException(Throwable t) {
        t.printStackTrace();
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "An internal server error occurred.");
    }
}
