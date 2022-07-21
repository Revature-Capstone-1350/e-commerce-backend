package com.revature.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.hash.Hashing;
import com.revature.dtos.*;
import com.revature.exceptions.*;
import com.revature.models.User;
import com.revature.models.UserRole;
import com.revature.repositories.UserRepository;
import com.revature.repositories.UserRoleRepository;
import com.revature.services.jwt.TokenService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;

@Service
public class AuthService {

    @Value("${secrets.salt}") // TODO : test whether salt is getting set or needs @PostConstruct
    private String salt;

    private final UserRepository userRepo;
    private final UserRoleRepository roleRepo;
    private final UserService userService;
    private final TokenService tokenService;
    private final ObjectMapper mapper = new ObjectMapper();

    public AuthService(UserRepository userRepo, UserRoleRepository roleRepo, UserService userService, TokenService tokenService) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @ResponseStatus(HttpStatus.OK)
    public Principal login(LoginRequest loginRequest) {

        // Validate credential & at this point, the credentials have been determined to be valid.
        return userRepo.findByEmailIgnoreCaseAndPassword(loginRequest.getEmail(), generatePassword(loginRequest.getPassword()))
                .map(Principal::new)
                .orElseThrow(UnauthorizedException::new);

        //return makeResp(user, HttpStatus.OK.value());
    }


    public UserResponse register(RegisterRequest registerRequest) {
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

        return userRepo.findById(user.getUserId()).map(UserResponse::new).orElseThrow(NotFoundException::new);
        //return makeResp(user, HttpStatus.CREATED.value());
    }

    private ResponseEntity makeResp(User user, int statusCode) {
        AuthResponse authResp = new AuthResponse(user); // init
        String resp = "";
        try {
            resp = mapper.writeValueAsString(authResp); // prepare JSON response
        } catch (JsonProcessingException e) {
            throw new BadRequestException();
        } // This throw is only anticipated to happen upon a bad request

        String token = getToken(user); // Generate a JWT
        HttpHeaders headers = new HttpHeaders(); // init
        headers.add("Authorization", token); // Set token in header

        return ResponseEntity
                .status(statusCode) // Set response status
                .headers(headers)   // Add the headers object
                .body(resp);        // Add the JSON response body
    }

    public String getToken(User user) {
        Principal prin = new Principal(user);
        if (user.getRole().getName().equalsIgnoreCase("Admin")) {
            prin.setIsAdmin(true); // this affects the token expiration time
        }
        return tokenService.generateToken(prin);
    }

    public void verifyToken(String token) {
        tokenService.extractTokenDetails(token);
    }

    public void adminCheck(String token) {
        Principal prin = tokenService.extractTokenDetails(token);
        System.out.println("Checking if admin: "+prin);
        User user = userService.findByIdAndEmailIgnoreCase(prin.getAuthUserId(), prin.getAuthUserEmail())
                .orElseThrow(BadRequestException::new); // user data in token not in DB
        System.out.println("User found: " + user);
        if (!user.getRole().getName().toString().equalsIgnoreCase("admin")) {
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
        } catch (Throwable e) {
            throw new RuntimeException();
        }
    }
    private String additionalHash(String string) {
        return (string == null) ? null : Hashing.sha256().
                hashString(string, StandardCharsets.UTF_8).toString();
    }

    // Leaving this to richard to fix (move all the response stuff to Controller class)
    public ResponseEntity updateUser(String token, ResetRequest resetRequest) {
        Principal principal = tokenService.extractTokenDetails(token); // get the principal from provided token
        User user = userRepo.getById(principal.getAuthUserId()); // find user by principal id
        String hashedOldPassword = generatePassword(resetRequest.getOldPassword());

        if (!hashedOldPassword.equals(user.getPassword())) { // if the old password doesn't match what we have on record...
            throw new UnauthorizedException(); // invalid password. possibly better exception could be used?
        }
        user.setPassword(generatePassword(resetRequest.getNewPassword())); // otherwise, generate the password
        userRepo.save(user); // save the repo
        return makeResp(user, HttpStatus.OK.value()); // all is good
    }
}

