package com.revature.services;

import com.revature.dtos.AuthResponse;
import com.revature.dtos.Principal;
import com.revature.models.User;
import com.revature.services.jwt.TokenService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserService userService;
    private final TokenService service;

    public AuthService(UserService userService, TokenService service) {
        this.userService = userService;
        this.service = service;
    }

    public Optional<User> findByCredentials(String email, String password) {
        return userService.findByCredentials(email, password);
    }

    public AuthResponse login(User user) {
        return new AuthResponse(userService.save(user));
    }


    public AuthResponse register(User user) {
        return new AuthResponse(userService.save(user));
    }

    public String getToken(User user) {return service.generateToken(new Principal(user));}

    public void verifyToken(String token) { service.extractTokenDetails(token); }
}
