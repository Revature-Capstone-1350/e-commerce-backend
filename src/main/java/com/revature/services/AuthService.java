package com.revature.services;

import com.revature.dtos.AuthResponse;
import com.revature.dtos.Principal;
import com.revature.dtos.RegisterRequest;
import com.revature.models.User;
import com.revature.services.jwt.TokenService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Optional;

@Service
public class AuthService {
    @Value("${secrets.salt}")
    private String salt;
    private final UserService userService;
    private final TokenService service;

    public AuthService(UserService userService, TokenService service) {
        this.userService = userService;
        this.service = service;
    }

    public Optional<User> findByCredentials(String email, String password) {
        return userService.findByCredentials(email, generatePassword(password));
    }

    public AuthResponse login(User user) {
        return new AuthResponse(userService.save(user));
    }


    public AuthResponse register(RegisterRequest request) {
        User newUser = new User(request);
        newUser.setPassword(generatePassword(newUser.getPassword()));
        return new AuthResponse(userService.save(newUser));
    }

    public String getToken(RegisterRequest request) {
        Principal user = new Principal(new User(request));
        return service.generateToken(user);
    }

    public String getToken(User user) {
        return service.generateToken(new Principal(user));
    }

    public void verifyToken(String token) {
        service.extractTokenDetails(token);
    }

    public void adminCheck(String token) {
        Principal prin = service.extractTokenDetails(token);
        User user = userService.findByIdAndEmailIgnoreCase(prin.getAuthUserId(), prin.getAuthUserEmail())
                .orElseThrow(RuntimeException::new); // TODO : 400 : user data in token not in DB
        if (!user.getRole().toString().equalsIgnoreCase("admin")) {
            // TODO : 403 error; must be admin
            throw new RuntimeException();
        }
        // no errors thrown, execution of program can continue
    }

    public String generatePassword(String password) {
        SecretKeyFactory factory;

        KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 128);
        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = factory.generateSecret(keySpec).getEncoded();
            return Base64.encodeBase64String(hash);
        } catch (Throwable e) {
            return null;
        }
    }
}

