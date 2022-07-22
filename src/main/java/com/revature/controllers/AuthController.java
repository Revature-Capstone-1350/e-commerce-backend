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
    private static final String AUTHORIZATION = "Authorization";
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
        resp.setHeader(AUTHORIZATION, token);
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
        resp.setHeader(AUTHORIZATION, token);
        return authResp;
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/update", consumes = "application/json", produces = "application/json")
    public AuthResponse updateUser(@RequestBody @Valid ResetRequest resetRequest, @RequestHeader(name = "Authorization") String tokenProvided, HttpServletResponse resp) {
        AuthResponse authResponse = authService.updateUser(tokenProvided, resetRequest);
        Principal payload = new Principal(authResponse.getId(), authResponse.getEmail()); // Constructs principal from authResponse
        resp.setHeader(AUTHORIZATION, tokenService.generateToken(payload));
        return authResponse;
    }
}
