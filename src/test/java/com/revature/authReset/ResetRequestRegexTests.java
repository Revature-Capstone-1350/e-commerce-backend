package com.revature.authReset;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.controllers.AuthController;
import com.revature.dtos.LoginRequest;
import com.revature.dtos.Principal;
import com.revature.dtos.ProductReviewRequest;
import com.revature.dtos.RegisterRequest;
import com.revature.models.User;
import com.revature.repositories.UserRepository;
import com.revature.repositories.UserRoleRepository;
import com.revature.services.AuthService;
import com.revature.services.jwt.TokenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static com.revature.util.ValidatorMessageUtil.*;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest // Tells Spring we need to have an entire application context with everything set up and ready to go
@AutoConfigureMockMvc // configures mockMvc
@DirtiesContext
public class ResetRequestRegexTests {

    private final MockMvc mockMvc;
    // allows to send HTTP requests and assert about their responses

    private final ObjectMapper mapper;
    private final UserRepository userRepo;
    private final AuthService authService;
    private final AuthController authCtrl;
    private final TokenService tokenService;
    private final String REGISTER_PATH = "/auth/register";
    private final String LOGIN_PATH = "/auth/login";
    private final String TEST_PATH = "/api/product/"+Integer.MAX_VALUE;
    private final String POST_REVIEW_PATH = "/api/product/rating/" + 1;
    private final String CONTENT_TYPE = "application/json";

    final String username = "Admin@skyview.com";
    final String oldPassword = "Admin12@";
    final String newPassword = "12@Admin";

    @Autowired
    public ResetRequestRegexTests(MockMvc mockMvc, ObjectMapper mapper, UserRepository userRepo, AuthService authService, AuthController authCtrl, TokenService tokenService) {
        this.mockMvc = mockMvc;
        this.mapper = mapper;
        this.userRepo = userRepo;
        this.authService = authService;
        this.authCtrl = authCtrl;
        this.tokenService = tokenService;
    }

    @Test
    void reset_req_gives_200_given_good_req() {
        User user = userRepo.findByEmailIgnoreCaseAndPassword(username, oldPassword)
                .orElseThrow(RuntimeException::new);
        String token = tokenService.generateToken(new Principal(user));
        // use token to hit reset endpoint with patch(PATH)
        // assert that password has changed
    }

    // new test just like above but with a new password lacking a number
    // assert isBadRequest; see Auth integration tests for examples

}
