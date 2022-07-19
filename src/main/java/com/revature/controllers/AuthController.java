package com.revature.controllers;

import com.revature.dtos.LoginRequest;
import com.revature.dtos.RegisterRequest;
import com.revature.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @ResponseStatus(HttpStatus.OK) // if successful, sets status of response
    @PostMapping(path="/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity login(@RequestBody @Valid LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @ResponseStatus(HttpStatus.CREATED) // if successful, sets status of response
    @PostMapping(path="/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity register(@RequestBody @Valid RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }
}
