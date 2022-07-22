package com.revature.controllers;

import com.revature.dtos.*;
import com.revature.services.AuthService;
import com.revature.services.jwt.TokenService;
import jdk.nashorn.internal.parser.Token;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final TokenService tokenService;
    public AuthController(AuthService authService, TokenService tokenService) {
        this.authService = authService;
        this.tokenService = tokenService;
    }

    @ResponseStatus(HttpStatus.OK) // if successful, sets status of response
    @PostMapping("/login") // @RequestBody @Valid did not work in service layer.
    public Principal login(@RequestBody @Valid LoginRequest loginRequest, HttpServletResponse resp) {
        Principal payload = authService.login(loginRequest);
        String token = tokenService.generateToken(payload);
        resp.setHeader("Authorization", token);
        return payload;
    }

    @ResponseStatus(HttpStatus.CREATED) // if successful, sets status of response
    @PostMapping("/register")
    public UserResponse register(@RequestBody @Valid RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/reset", consumes = "application/json")
    public void resetPassword(@RequestBody @Valid ResetRequest resetRequest, @RequestHeader(name = "Authorization") String token) {
        authService.updateUser(token, resetRequest);
    }
}
