package com.revature.advice;

import com.revature.annotations.AdminOnly;
import com.revature.annotations.Authorized;
import com.revature.services.AuthService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class AuthAspect {

    // Spring will autowire a proxy object for the request
    // It isn't a request object itself, but if there is an active request
    // the proxy will pass method calls to the real request
    private final HttpServletRequest req;
    private final AuthService authService;

    public AuthAspect(HttpServletRequest req, AuthService authService) {
        this.req = req;
        this.authService = authService;
    }

    // Calls service layer to verify user ID and email are of an admin account

    @Around("@annotation(adminOnly)")
    public Object authenticate(ProceedingJoinPoint pjp, AdminOnly adminOnly) throws Throwable {
        authService.adminCheck(req.getHeader("Authorization")); // Checks if user is an admin
        return pjp.proceed(pjp.getArgs()); // Call the originally intended method
    }
}
