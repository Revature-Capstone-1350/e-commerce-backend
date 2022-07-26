package com.revature.authReset;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.controllers.AuthController;
import com.revature.dtos.Principal;
import com.revature.dtos.ResetRequest;
import com.revature.models.User;
import com.revature.repositories.UserRepository;
import com.revature.services.AuthService;
import com.revature.services.jwt.TokenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest // Tells Spring we need to have an entire application context with everything set up and ready to go
@AutoConfigureMockMvc // configures mockMvc
@DirtiesContext
class ResetRequestRegexTests {

    private final MockMvc mockMvc;
    // allows to send HTTP requests and assert about their responses

    private final ObjectMapper mapper;
    private final UserRepository userRepo;
    private final AuthService authService;
    private final TokenService tokenService;
    private final String RESET_PATH = "/auth/reset";
    private final String CONTENT_TYPE = "application/json";

    final String username = "Admin@SkyView.com";
    final String oldPassword = "Admin12@";
    final String newPassword = "12@Admin";

    @Autowired
    public ResetRequestRegexTests(MockMvc mockMvc, ObjectMapper mapper, UserRepository userRepo, AuthService authService, TokenService tokenService) {
        this.mockMvc = mockMvc;
        this.mapper = mapper;
        this.userRepo = userRepo;
        this.authService = authService;
        this.tokenService = tokenService;
    }

    @Test // Regex for reset request does not fire if password input is blank
    void reset_req_gives_200_given_good_req() throws Exception {
        User user = userRepo.findByEmailIgnoreCaseAndPassword(
                username, authService.generatePassword(oldPassword)
                ).orElseThrow(RuntimeException::new);
        assertEquals(1, user.getUserId());
        String token = tokenService.generateToken(new Principal(user));

        ResetRequest resetRequest = new ResetRequest(oldPassword,"","","","");
        String reqJSON = mapper.writeValueAsString(resetRequest);
        String newToken =
                mockMvc.perform(
                        patch(RESET_PATH)
                                .contentType(CONTENT_TYPE)
                                .content(reqJSON)
                                .header("Authorization", token)
                        )
                .andExpect(status().isOk())
                .andExpect(header().string("content-type", CONTENT_TYPE))
                .andExpect(header().string("Access-Control-Allow-Origin", "*"))
                .andExpect(header().string("Access-Control-Allow-Methods", "*"))
                .andExpect(header().string("Access-Control-Allow-Headers", "*"))
                .andExpect(header().exists("Authorization"))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.id").value(user.getUserId()))
                .andExpect(jsonPath("$.email").exists())
                .andExpect(jsonPath("$.email").isString())
                .andExpect(jsonPath("$.email").value(username))
                .andExpect(jsonPath("$.role").exists())
                .andExpect(jsonPath("$.role").isString())
                .andExpect(jsonPath("$.role").value("ADMIN"))
                .andExpect(jsonPath("$.firstName").exists())
                .andExpect(jsonPath("$.firstName").isString())
                .andExpect(jsonPath("$.firstName").value("Admin"))
                .andExpect(jsonPath("$.lastName").exists())
                .andExpect(jsonPath("$.lastName").isString())
                .andExpect(jsonPath("$.lastName").value("Admin"))
                .andReturn().getResponse().getHeader("Authorization");
        Principal prin = tokenService.extractTokenDetails(newToken);
        assertEquals(prin.getAuthUserEmail(), username);
        assertEquals(prin.getAuthUserId(), user.getUserId());

        User userAfter = userRepo.findByEmailIgnoreCaseAndPassword(
                username, authService.generatePassword(oldPassword)
        ).orElseThrow(RuntimeException::new);
        // password unchanged
    }

    @Test // Regex for reset request fires when newPassword is not blank
    void reset_req_gives_errors_given_bad_req() throws Exception {
        User user = userRepo.findByEmailIgnoreCaseAndPassword(
                username, authService.generatePassword(oldPassword)
        ).orElseThrow(RuntimeException::new);
        assertEquals(1, user.getUserId());
        String token = tokenService.generateToken(new Principal(user));

        String newPassword = " ";

        ResetRequest resetRequest = new ResetRequest(oldPassword,newPassword,"","","");
        String reqJSON = mapper.writeValueAsString(resetRequest);
        mockMvc.perform(patch(RESET_PATH)
                                .contentType(CONTENT_TYPE)
                                .content(reqJSON)
                                .header("Authorization", token)
                        )
                        .andExpect(status().isBadRequest())
                        .andExpect(header().string("content-type", CONTENT_TYPE))
                        .andExpect(header().string("Access-Control-Allow-Origin", "*"))
                        .andExpect(header().string("Access-Control-Allow-Methods", "*"))
                        .andExpect(header().string("Access-Control-Allow-Headers", "*"))
                        .andExpect(header().doesNotExist("Authorization"))
                        .andExpect(jsonPath("$.id").doesNotExist())
                        .andExpect(jsonPath("$.email").doesNotExist())
                        .andExpect(jsonPath("$.role").doesNotExist())
                        .andExpect(jsonPath("$.firstName").doesNotExist())
                        .andExpect(jsonPath("$.lastName").doesNotExist())
                        .andExpect(jsonPath("$.statusCode").exists())
                        .andExpect(jsonPath("$.statusCode").isNumber())
                        .andExpect(jsonPath("$.statusCode").value(400))
                        .andExpect(jsonPath("$.message").exists())
                        .andExpect(jsonPath("$.message").isArray())
                        .andExpect(jsonPath("$.timestamp").exists())
                        .andExpect(jsonPath("$.timestamp").isNotEmpty())
                        .andReturn();
        User userAfter = userRepo.findByEmailIgnoreCaseAndPassword(
                username, authService.generatePassword(oldPassword)
        ).orElseThrow(RuntimeException::new);
        // password unchanged
    }
}
