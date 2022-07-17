package com.revature.controllers;

import com.revature.dtos.LoginRequest;
import com.revature.dtos.RegisterRequest;
import com.revature.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
//@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"}, allowCredentials = "true")
public class AuthController {

    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login") // @RequestBody @Valid did not work in service layer.
    public ResponseEntity login(@RequestBody @Valid LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }
}
