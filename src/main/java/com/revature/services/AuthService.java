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
    @Value("${jwt.salt}")
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

    public void verifyToken(String token) {
        service.extractTokenDetails(token);
    }

    public String generatePassword(String password) {
        SecretKeyFactory factory;

        KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 128);
        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = factory.generateSecret(keySpec).getEncoded();
            return Base64.encodeBase64String(hash);
        } catch (NoSuchAlgorithmException e) {
            return null;
        } catch (InvalidKeySpecException e) {
            return null;
        }
    }
}

