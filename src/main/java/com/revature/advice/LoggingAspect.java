package com.revature.advice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component  // Creating an aspect component to inject log data
public class LoggingAspect {

    private final Logger logger = LogManager.getLogger();

    @Pointcut("within(com.revature..*)")
    public void logAll() {}

    // Injecting log before method execution
    @Before(value = "logAll()")
    public void logMethodStart(JoinPoint jp) {
        String methodSig = extractMethodSignature(jp);
        String methodArgs = Arrays.toString(jp.getArgs());
        logger.info("{} invoked with provided args: {}", methodSig, methodArgs);
    }

    // Injecting log after a method returns an object
    @AfterReturning(value = "logAll()", returning = "returnedObj")
    public void logMethodReturn(JoinPoint jp, Object returnedObj) {
        String methodSig = extractMethodSignature(jp);
        logger.info("{} successfully returned with the value: {}", methodSig, returnedObj);
    }

    // Injecting log after a method throws an exception
    @AfterThrowing(value = "logAll()", throwing = "t")
    public void logMethodException(JoinPoint jp, Throwable t) {
        String methodSig = extractMethodSignature(jp);
        String exceptionName = t.getClass().getSimpleName();
        logger.warn("{} was thrown in method {} with the message \"{}\"", exceptionName, methodSig, t.getMessage());
    }

    // Helper method used to extract the target method signature/name
    private String extractMethodSignature(JoinPoint jp) {
        return jp.getTarget().getClass().getSimpleName() + "#" + jp.getSignature().getName();
    }
}
