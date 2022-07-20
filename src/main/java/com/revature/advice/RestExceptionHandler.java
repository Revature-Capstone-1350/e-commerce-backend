package com.revature.advice;

import com.revature.dtos.ErrorResponse;
import com.revature.exceptions.*;
import org.apache.catalina.filters.ExpiresFilter;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

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
       ex.getBindingResult().getFieldErrors().forEach(error-> {
           errors.add(error.getDefaultMessage());
       });
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
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                new ArrayList<String>() {{add("Invalid Input.");}}
                );
    }

//    // Specific 400
//    // This handles error thrown via the DTO validation annotations
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ErrorResponse handleMethodArgumentNotValidException(
//            MethodArgumentNotValidException e
//    ) {
//        e.printStackTrace();
//        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
//                e.getLocalizedMessage()); // TODO : this
//    }

    // Generic 401
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public ErrorResponse handleUnauthorizedException(Throwable t) {
        t.printStackTrace();
        return new ErrorResponse(HttpStatus.UNAUTHORIZED.value(),
                "Invalid Credentials.");
    }

    // Specific 401
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    @ExceptionHandler(TokenParseException.class)
//    public ErrorResponse handleTokenParseException(Throwable t) {
//        t.printStackTrace();
//        return new ErrorResponse(HttpStatus.UNAUTHORIZED.value(),
//                "Login session expired. Please login again.");
//    }

    // Generic 403
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    @ExceptionHandler(ForbiddenException.class)
//    public ErrorResponse handleForbiddenException(Throwable t) {
//        t.printStackTrace();
//        return new ErrorResponse(HttpStatus.FORBIDDEN.value(),
//                "Access Denied");
//    }

    // Generic 404
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(NotFoundException.class)
//    public ErrorResponse handleNotFoundException(Throwable t) {
//        t.printStackTrace();
//        return new ErrorResponse(HttpStatus.NOT_FOUND.value(),
//                "Resource not found.");
//    }

    // Generic 409
//    @ResponseStatus(HttpStatus.CONFLICT)
//    @ExceptionHandler(ConflictException.class)
//    public ErrorResponse handleConflictException(Throwable t) {
//        t.printStackTrace();
//        return new ErrorResponse(HttpStatus.CONFLICT.value(),
//                "There is already a resource with those specifications.");
//    }

    // Generic 500
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler
//    public ErrorResponse handleOtherException(Throwable t) {
//        t.printStackTrace();
//        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
//                "An internal server error occurred.");
//    }
}
