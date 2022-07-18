package com.revature.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dtos.RegisterRequest;
import com.revature.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

// Test disabled for now
//@SpringBootTest // Tells Spring we need to have an entire application context with everything set up and ready to go
//@AutoConfigureMockMvc // configures mockMvc
class UserRegistrationIntegrationTest {

    private final MockMvc mockMvc;
    // allows to send HTTP requests and assert about their responses

    private final ObjectMapper mapper;
    private final UserRepository userRepo;
    private final String PATH = "/auth/register";
    private final String CONTENT_TYPE = "application/json";


    @Autowired
    UserRegistrationIntegrationTest(MockMvc mockMvc, ObjectMapper mapper, UserRepository userRepo) { // Spring gives us a configured MockMvc
        this.mockMvc = mockMvc; // This is a Spring-provided object, so we wire it in
        this.mapper = mapper;
        this.userRepo = userRepo;
    }

    @Test
    void test_register_user_returns200givenValidRegisterRequest() throws Exception {
        RegisterRequest newRegistrationRequest = new RegisterRequest();
        newRegistrationRequest.setEmail("valid.user@valid.org");
        newRegistrationRequest.setFirstName("Valid");
        newRegistrationRequest.setLastName("AlsoValid");
        newRegistrationRequest.setPassword("!@#123qweQWEasd");

        String requestPayload = mapper.writeValueAsString(newRegistrationRequest);

        mockMvc.perform(post(PATH).contentType(CONTENT_TYPE).content(requestPayload))
                .andExpect(status().isOk())
                .andExpect(header().string("content-type", CONTENT_TYPE))
                .andExpect(header().string("Access-Control-Allow-Origin", "*"))
                .andExpect(header().string("Access-Control-Allow-Methods", "*"))
                .andExpect(header().string("Access-Control-Allow-Headers", "*"))
                .andExpect(header().exists("Authorization"))
                .andExpect(jsonPath("$.resourceId").exists())
                .andExpect(jsonPath("$.resourceId").isString())
                .andReturn();
        assertTrue(userRepo.existsByEmailIgnoreCase(newRegistrationRequest.getEmail()));
    }

}
