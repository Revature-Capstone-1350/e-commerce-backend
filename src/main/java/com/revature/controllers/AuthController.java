package com.revature.controllers;

import com.revature.dtos.AuthResponse;
import com.revature.dtos.LoginRequest;
import com.revature.dtos.RegisterRequest;
import com.revature.models.User;
import com.revature.services.AuthService;
import org.springframework.http.HttpHeaders;
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

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        Optional<User> optional = authService.findByCredentials(loginRequest.getEmail(), loginRequest.getPassword());

        if(!optional.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        HttpHeaders respHeaders = new HttpHeaders();
        // todo: fix
        respHeaders.set("Authorization", authService.getToken(optional.get()));

        AuthResponse authResp = new AuthResponse(optional.get());

        return ResponseEntity.ok().headers(respHeaders).body(authResp);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid RegisterRequest registerRequest) {
        AuthResponse authResp = authService.register(registerRequest); // try to register before making token

        HttpHeaders respHeaders = new HttpHeaders();
        respHeaders.set("Authorization", authService.getToken(registerRequest));

        return ResponseEntity.status(HttpStatus.CREATED).headers(respHeaders).body(authResp);
    }
}
