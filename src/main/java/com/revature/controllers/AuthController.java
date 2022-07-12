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

import java.util.Optional;

import static com.revature.services.security.Hashv1.encrypt;

@RestController
@RequestMapping("/auth")
//@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"}, allowCredentials = "true")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        Optional<User> optional = authService.findByCredentials(loginRequest.getEmail(), encrypt(loginRequest.getPassword()));

        if(!optional.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        HttpHeaders respHeaders = new HttpHeaders();
        respHeaders.set("Authorization", authService.getToken(optional.get()));

        AuthResponse authResp = new AuthResponse(optional.get());

        return ResponseEntity.ok().headers(respHeaders).body(authResp);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest) {
        registerRequest.setPassword(encrypt(registerRequest.getPassword()));
        User created = new User(registerRequest);

        HttpHeaders respHeaders = new HttpHeaders();
        respHeaders.set("Authorization", authService.getToken(created));

        AuthResponse authResp = new AuthResponse(authService.register(created));

        return ResponseEntity.status(HttpStatus.CREATED).headers(respHeaders).body(authResp);
    }
}
