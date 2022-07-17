package com.revature.services;

import com.revature.dtos.AuthResponse;
import com.revature.dtos.LoginRequest;
import com.revature.dtos.Principal;
import com.revature.dtos.RegisterRequest;
import com.revature.exceptions.NotImplementedException;
import com.revature.models.User;
import com.revature.services.jwt.TokenService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.validation.Valid;
import java.security.spec.KeySpec;
import java.util.Optional;

@Service
public class AuthService {

    @Value("${secrets.salt}")
    private String salt;

    private final UserService userService;
    private final TokenService tokenService;

    public AuthService(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    public AuthResponse login(@Valid LoginRequest loginRequest) {
        throw new NotImplementedException();
    }


    public AuthResponse register(@Valid RegisterRequest registerRequest) {
        throw new NotImplementedException();
    }

    public String getToken(RegisterRequest request) {
        Principal user = new Principal(new User(request));
        return tokenService.generateToken(user);
    }

    public String getToken(User user) {
        return tokenService.generateToken(new Principal(user));
    }

    public void verifyToken(String token) {
        tokenService.extractTokenDetails(token);
    }

    public void adminCheck(String token) {
        Principal prin = tokenService.extractTokenDetails(token);
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

