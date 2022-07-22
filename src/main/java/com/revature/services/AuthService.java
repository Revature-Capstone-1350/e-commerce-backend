package com.revature.services;

import com.revature.dtos.*;
import com.revature.exceptions.*;
import com.revature.models.User;
import com.revature.models.UserRole;
import com.revature.repositories.UserRepository;
import com.revature.repositories.UserRoleRepository;
import com.revature.services.jwt.TokenService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.spec.KeySpec;

@Service
public class AuthService {

    @Value("${secrets.salt}")
    private String salt;

    private final UserRepository userRepo;
    private final UserRoleRepository roleRepo;
    private final UserService userService;
    private final TokenService tokenService;

    public AuthService(UserRepository userRepo, UserRoleRepository roleRepo, UserService userService, TokenService tokenService) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @ResponseStatus(HttpStatus.OK)
    public AuthResponse login(LoginRequest loginRequest) {

        // Validate credential & at this point, the credentials have been determined to be valid.
        return userRepo.findByEmailIgnoreCaseAndPassword(loginRequest.getEmail(), generatePassword(loginRequest.getPassword()))
                .map(AuthResponse::new)
                .orElseThrow(UnauthorizedException::new);
    }

    public AuthResponse getAuthResponseFromLogin(LoginRequest loginRequest) {
        User user = userRepo.findByEmailIgnoreCaseAndPassword(
                loginRequest.getEmail(),
                generatePassword(loginRequest.getPassword())
        ).orElseThrow(UnauthorizedException::new);
        return new AuthResponse(user);
    }

    public AuthResponse register(RegisterRequest registerRequest) {
        // First, check if email is already taken
        if (userRepo.existsByEmailIgnoreCase(registerRequest.getEmail())) {
            throw new ConflictException(); // Gives generic response
        }
        // The DTO annotations validated the input and the email is available
        registerRequest.setPassword(
                generatePassword( registerRequest.getPassword() )
        );
        User user = new User(registerRequest);
        UserRole basicRole = roleRepo.findByNameIgnoreCase("Basic").orElseThrow(RuntimeException::new);
        // RuntimeException because the server should be able to find this role

        user.setRole(basicRole);
        user = userRepo.save(user);

        return userRepo.findById(user.getUserId()).map(AuthResponse::new).orElseThrow(NotFoundException::new);
    }

    public String getToken(User user) {
        Principal prin = new Principal(user);
        return tokenService.generateToken(prin);
    }

    public void verifyToken(String token) {
        tokenService.extractTokenDetails(token);
    }

    public void adminCheck(String token) {
        Principal prin = tokenService.extractTokenDetails(token);
        User user = userService.findByIdAndEmailIgnoreCase(prin.getAuthUserId(), prin.getAuthUserEmail())
                .orElseThrow(BadRequestException::new); // user data in token not in DB
        if (!user.getRole().getName().equalsIgnoreCase("admin")) {
            throw new ForbiddenException(); // 403 error; must be admin
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
        } catch (Exception e) {
            throw new InternalServerErrorException();
        }
    }

    public AuthResponse updateUser(String token, ResetRequest resetRequest) {

        Principal principal = tokenService.extractTokenDetails(token); // get the principal from provided token
        User user = userRepo.findByUserIdAndEmailIgnoreCase(principal.getAuthUserId(), principal.getAuthUserEmail()).orElseThrow(UnauthorizedException::new);
        String hashedOldPassword = generatePassword(resetRequest.getOldPassword());

        // this code goes through each field in resetrequest to check if anything is null
        if (!hashedOldPassword.equals(user.getPassword())) { // if the old password doesn't match what we have on record...
            throw new UnauthorizedException(); // invalid password. possibly better exception could be used?
        }

        if (resetRequest.getNewPassword() != null) {
            user.setPassword(generatePassword(resetRequest.getNewPassword()));
        }

        if (resetRequest.getNewFirstname() != null) {
            user.setFirstName(resetRequest.getNewFirstname());
        }

        if (resetRequest.getNewLastname() != null) {
            user.setLastName(resetRequest.getNewLastname());
        }

        if (resetRequest.getNewEmail() != null) {
            if (userRepo.existsByEmailIgnoreCase(resetRequest.getNewEmail())) {
                throw new ConflictException();
            } else {
                user.setEmail(resetRequest.getNewEmail());
            }
        }
        return new AuthResponse(userRepo.save(user));
    }
}

