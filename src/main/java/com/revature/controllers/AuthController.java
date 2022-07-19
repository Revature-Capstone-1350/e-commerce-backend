package com.revature.controllers;

import com.revature.dtos.LoginRequest;
import com.revature.dtos.RegisterRequest;
import com.revature.dtos.ResetRequest;
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
    @PostMapping("/login") // @RequestBody @Valid did not work in service layer.
    public ResponseEntity login(@RequestBody @Valid LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @ResponseStatus(HttpStatus.CREATED) // if successful, sets status of response
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/reset", consumes = "application/json")
    public ResponseEntity resetPassword(@RequestBody @Valid ResetRequest resetRequest, @RequestHeader String authorization) {
        return authService.updateUser(authorization, resetRequest);
    }
}
