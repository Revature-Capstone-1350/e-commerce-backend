package com.revature.controllers;

import com.revature.dtos.*;
import com.revature.services.AuthService;
import com.revature.services.jwt.TokenService;
import org.springframework.http.HttpStatus;
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
    public AuthResponse login(@RequestBody @Valid LoginRequest loginRequest, HttpServletResponse resp) {
        AuthResponse authResp = authService.login(loginRequest);
        Principal payload = new Principal( // construct principle from authresp
                authResp.getId(),
                authResp.getEmail()
        );
        String token = tokenService.generateToken(payload);
        resp.setHeader("Authorization", token);
        return authResp;
    }

    @ResponseStatus(HttpStatus.CREATED) // if successful, sets status of response
    @PostMapping("/register")
    public AuthResponse register(@RequestBody @Valid RegisterRequest registerRequest, HttpServletResponse resp) {
        AuthResponse authResp = authService.register(registerRequest);
        Principal payload = new Principal( // construct principle from authresp
                authResp.getId(),
                authResp.getEmail()
        );
        String token = tokenService.generateToken(payload);
        resp.setHeader("Authorization", token);
        return authResp;
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/reset", consumes = "application/json")
    public void resetPassword(@RequestBody @Valid ResetRequest resetRequest, @RequestHeader(name = "Authorization") String token) {
        authService.updateUser(token, resetRequest);
    }
}
