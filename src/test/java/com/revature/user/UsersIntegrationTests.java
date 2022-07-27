package com.revature.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.controllers.AuthController;
import com.revature.repositories.UserRepository;
import com.revature.repositories.UserRoleRepository;
import com.revature.services.AuthService;
import com.revature.services.jwt.TokenService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest // Tells Spring we need to have an entire application context with everything set up and ready to go
@AutoConfigureMockMvc // configures mockMvc
public class UsersIntegrationTests {
    private final MockMvc mockMvc;
    // allows to send HTTP requests and assert about their responses

    private final ObjectMapper mapper;
    private final UserRepository userRepo;
    private final UserRoleRepository roleRepo;
    private final AuthService authService;
    private final AuthController authCtrl;
    private final TokenService tokenService;
    private final String REGISTER_PATH = "/auth/register";
    private final String LOGIN_PATH = "/auth/login";
    private final String CREATE_PRODUCT_PATH = "/api/product/createproduct";
    private final String POST_REVIEW_PATH = "/api/product/rating/" + 1;
    private final String CONTENT_TYPE = "application/json";

    public UsersIntegrationTests(MockMvc mockMvc, ObjectMapper mapper, UserRepository userRepo, UserRoleRepository roleRepo, AuthService authService, AuthController authCtrl, TokenService tokenService) {
        this.mockMvc = mockMvc;
        this.mapper = mapper;
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.authService = authService;
        this.authCtrl = authCtrl;
        this.tokenService = tokenService;
    }

    @Test
    void admin_can_get_all_users() throws Exception {

    }

    @Test
    void non_admin_cannot_get_all_users() throws Exception {

    }

    @Test
    void admin_can_get_user_by_id() throws Exception {

    }

    @Test
    void user_can_find_self_by_id() throws Exception {

    }

    @Test
    void user_cannot_find_different_user_by_id() throws Exception {

    }
}
