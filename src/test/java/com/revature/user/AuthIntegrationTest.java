package com.revature.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dtos.LoginRequest;
import com.revature.dtos.RegisterRequest;
import com.revature.models.User;
import com.revature.repositories.UserRepository;
import com.revature.services.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

// Test disabled for now
@SpringBootTest // Tells Spring we need to have an entire application context with everything set up and ready to go
@AutoConfigureMockMvc // configures mockMvc
@DirtiesContext
class AuthIntegrationTest {

    private final MockMvc mockMvc;
    // allows to send HTTP requests and assert about their responses

    private final ObjectMapper mapper;
    private final UserRepository userRepo;
    private final AuthService authService;
    private final String REGISTER_PATH = "/auth/register";
    private final String LOGIN_PATH = "/auth/login";
    private final String TEST_PATH = "/api/product/"+Integer.MAX_VALUE;
    private final String CONTENT_TYPE = "application/json";


    @Autowired
    AuthIntegrationTest(MockMvc mockMvc, ObjectMapper mapper, UserRepository userRepo, AuthService authService) { // Spring gives us a configured MockMvc
        this.mockMvc = mockMvc; // This is a Spring-provided object, so we wire it in
        this.mapper = mapper;
        this.userRepo = userRepo;
        this.authService = authService;
    }


    String validEmail = UUID.randomUUID().toString().replace("-","")+"@valid.org";
    // Constructs a random valid email

    String validPass = randomPass();
    String validFirst = "Valid";
    String validLast = "AlsoValid";

    @Test
    void test_register_login_and_adminOnly_annotation() throws Exception {

        // First block : show that login fails because user is not in the system

        LoginRequest newLoginRequest = new LoginRequest();
        newLoginRequest.setEmail(validEmail);
        newLoginRequest.setPassword(validPass);

        String loginRequest = mapper.writeValueAsString(newLoginRequest);

        mockMvc.perform(post(LOGIN_PATH).contentType(CONTENT_TYPE).content(loginRequest))
                .andExpect(status().isUnauthorized())
                .andExpect(header().string("content-type", CONTENT_TYPE))
                .andExpect(header().string("Access-Control-Allow-Origin", "*"))
                .andExpect(header().string("Access-Control-Allow-Methods", "*"))
                .andExpect(header().string("Access-Control-Allow-Headers", "*"))
                .andExpect(jsonPath("$.statusCode").exists())
                .andExpect(jsonPath("$.statusCode").isNumber())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message").isString())
                .andExpect(jsonPath("$.message").value("Invalid Credentials."))
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.timestamp").isNotEmpty())
                .andReturn();
        assertFalse(userRepo.existsByEmailIgnoreCase(validEmail));

        // Second block : register the user

        RegisterRequest newRegistrationRequest = new RegisterRequest();
        newRegistrationRequest.setEmail(validEmail);
        newRegistrationRequest.setFirstName(validFirst);
        newRegistrationRequest.setLastName(validLast);
        newRegistrationRequest.setPassword(validPass);
        String registerRequest = mapper.writeValueAsString(newRegistrationRequest);
        stars();
        System.out.println(registerRequest);
        stars();
        String token =
        mockMvc.perform(post(REGISTER_PATH).contentType(CONTENT_TYPE).content(registerRequest))
                .andExpect(status().isCreated())
                .andExpect(header().string("content-type", CONTENT_TYPE))
                .andExpect(header().string("Access-Control-Allow-Origin", "*"))
                .andExpect(header().string("Access-Control-Allow-Methods", "*"))
                .andExpect(header().string("Access-Control-Allow-Headers", "*"))
                .andExpect(header().exists("Authorization"))
                .andExpect(jsonPath("$.email").exists())
                .andExpect(jsonPath("$.email").isString())
                .andExpect(jsonPath("$.email").value(validEmail))
                .andExpect(jsonPath("$.role").exists())
                .andExpect(jsonPath("$.role").isString())
                .andExpect(jsonPath("$.role").value("BASIC"))
                .andExpect(jsonPath("$.firstName").exists())
                .andExpect(jsonPath("$.firstName").isString())
                .andExpect(jsonPath("$.firstName").value(validFirst))
                .andExpect(jsonPath("$.lastName").exists())
                .andExpect(jsonPath("$.lastName").isString())
                .andExpect(jsonPath("$.lastName").value(validLast))
                .andReturn().getResponse().getHeader("Authorization");
        assertTrue(userRepo.existsByEmailIgnoreCase(validEmail));

        User user = userRepo
                .findByEmailIgnoreCaseAndPassword(validEmail,authService.generatePassword(validPass))
                .orElseThrow(RuntimeException::new);
        assertTrue(user.getRole().getName().equalsIgnoreCase("Basic"));

//         Third block : Utilize token to access @AdminOnly endpoint
        mockMvc.perform(delete(TEST_PATH)
                        .contentType(CONTENT_TYPE)
                        .header("Authorization", token))
                .andExpect(status().isForbidden())
                .andExpect(header().string("content-type", CONTENT_TYPE))
                .andExpect(header().string("Access-Control-Allow-Origin", "*"))
                .andExpect(header().string("Access-Control-Allow-Methods", "*"))
                .andExpect(header().string("Access-Control-Allow-Headers", "*"))
                .andExpect(jsonPath("$.statusCode").exists())
                .andExpect(jsonPath("$.statusCode").isNumber())
                .andExpect(jsonPath("$.statusCode").value(403))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message").isString())
                .andExpect(jsonPath("$.message").value("Access Denied"))
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.timestamp").isNotEmpty())
                .andReturn();


    }

    @Test
    void test_login_dto_no_number() throws Exception {
        LoginRequest req = new LoginRequest();
        req.setEmail(validEmail);
        req.setPassword("aA@@@@@@");
        String loginRequest = mapper.writeValueAsString(req);
        mockMvc.perform(post(LOGIN_PATH).contentType(CONTENT_TYPE).content(loginRequest))
                .andExpect(status().isBadRequest())
                .andExpect(header().string("content-type", CONTENT_TYPE))
                .andExpect(header().string("Access-Control-Allow-Origin", "*"))
                .andExpect(header().string("Access-Control-Allow-Methods", "*"))
                .andExpect(header().string("Access-Control-Allow-Headers", "*"))
                .andExpect(jsonPath("$.statusCode").exists())
                .andExpect(jsonPath("$.statusCode").isNumber())
                .andExpect(jsonPath("$.statusCode").value(400))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message").isString())
                .andExpect(jsonPath("$.message").value("Invalid Input."))
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.timestamp").isNotEmpty())
                .andReturn();
    }
    @Test
    void test_login_dto_no_lowercase() throws Exception {
        LoginRequest req = new LoginRequest();
        req.setEmail(validEmail);
        req.setPassword("1A@@@@@@");
        assert(req.getPassword().length() >= 8);
        String loginRequest = mapper.writeValueAsString(req);
        mockMvc.perform(post(LOGIN_PATH).contentType(CONTENT_TYPE).content(loginRequest))
                .andExpect(status().isBadRequest())
                .andExpect(header().string("content-type", CONTENT_TYPE))
                .andExpect(header().string("Access-Control-Allow-Origin", "*"))
                .andExpect(header().string("Access-Control-Allow-Methods", "*"))
                .andExpect(header().string("Access-Control-Allow-Headers", "*"))
                .andExpect(jsonPath("$.statusCode").exists())
                .andExpect(jsonPath("$.statusCode").isNumber())
                .andExpect(jsonPath("$.statusCode").value(400))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message").isString())
                .andExpect(jsonPath("$.message").value("Invalid Input."))
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.timestamp").isNotEmpty())
                .andReturn();
    }
    @Test
    void test_login_dto_no_uppercase() throws Exception {
        LoginRequest req = new LoginRequest();
        req.setEmail(validEmail);
        req.setPassword("1a@@@@@@");
        assert(req.getPassword().length() >= 8);
        String loginRequest = mapper.writeValueAsString(req);
        mockMvc.perform(post(LOGIN_PATH).contentType(CONTENT_TYPE).content(loginRequest))
                .andExpect(status().isBadRequest())
                .andExpect(header().string("content-type", CONTENT_TYPE))
                .andExpect(header().string("Access-Control-Allow-Origin", "*"))
                .andExpect(header().string("Access-Control-Allow-Methods", "*"))
                .andExpect(header().string("Access-Control-Allow-Headers", "*"))
                .andExpect(jsonPath("$.statusCode").exists())
                .andExpect(jsonPath("$.statusCode").isNumber())
                .andExpect(jsonPath("$.statusCode").value(400))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message").isString())
                .andExpect(jsonPath("$.message").value("Invalid Input."))
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.timestamp").isNotEmpty())
                .andReturn();
    }
    @Test
    void test_login_dto_no_special_char() throws Exception {
        LoginRequest req = new LoginRequest();
        req.setEmail(validEmail);
        req.setPassword("1Aaaaaaa");
        assert(req.getPassword().length() >= 8);
        String loginRequest = mapper.writeValueAsString(req);
        mockMvc.perform(post(LOGIN_PATH).contentType(CONTENT_TYPE).content(loginRequest))
                .andExpect(status().isBadRequest())
                .andExpect(header().string("content-type", CONTENT_TYPE))
                .andExpect(header().string("Access-Control-Allow-Origin", "*"))
                .andExpect(header().string("Access-Control-Allow-Methods", "*"))
                .andExpect(header().string("Access-Control-Allow-Headers", "*"))
                .andExpect(jsonPath("$.statusCode").exists())
                .andExpect(jsonPath("$.statusCode").isNumber())
                .andExpect(jsonPath("$.statusCode").value(400))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message").isString())
                .andExpect(jsonPath("$.message").value("Invalid Input."))
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.timestamp").isNotEmpty())
                .andReturn();
    }
    @Test
    void test_login_dto_length7() throws Exception {
        LoginRequest req = new LoginRequest();
        req.setEmail(validEmail);
        req.setPassword("1A@aaa");
        assert(req.getPassword().length() < 8);
        String loginRequest = mapper.writeValueAsString(req);
        mockMvc.perform(post(LOGIN_PATH).contentType(CONTENT_TYPE).content(loginRequest))
                .andExpect(status().isBadRequest())
                .andExpect(header().string("content-type", CONTENT_TYPE))
                .andExpect(header().string("Access-Control-Allow-Origin", "*"))
                .andExpect(header().string("Access-Control-Allow-Methods", "*"))
                .andExpect(header().string("Access-Control-Allow-Headers", "*"))
                .andExpect(jsonPath("$.statusCode").exists())
                .andExpect(jsonPath("$.statusCode").isNumber())
                .andExpect(jsonPath("$.statusCode").value(400))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message").isString())
                .andExpect(jsonPath("$.message").value("Invalid Input."))
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.timestamp").isNotEmpty())
                .andReturn();
    }
    @Test
    void test_login_dto_correct_input() throws Exception {
        LoginRequest req = new LoginRequest();
        req.setEmail(validEmail);
        req.setPassword("11AA@@$$aa");
        assert(req.getPassword().length() >= 8);
        assertFalse(userRepo.existsByEmailIgnoreCase(validEmail));
        String loginRequest = mapper.writeValueAsString(req);
        mockMvc.perform(post(LOGIN_PATH).contentType(CONTENT_TYPE).content(loginRequest))
                .andExpect(status().isUnauthorized())
                .andExpect(header().string("content-type", CONTENT_TYPE))
                .andExpect(header().string("Access-Control-Allow-Origin", "*"))
                .andExpect(header().string("Access-Control-Allow-Methods", "*"))
                .andExpect(header().string("Access-Control-Allow-Headers", "*"))
                .andExpect(jsonPath("$.statusCode").exists())
                .andExpect(jsonPath("$.statusCode").isNumber())
                .andExpect(jsonPath("$.statusCode").value(401))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message").isString())
                .andExpect(jsonPath("$.message").value("Invalid Credentials."))
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.timestamp").isNotEmpty())
                .andReturn();
    }
    @Test
    void test_login_dto_random_pass() throws Exception {
        LoginRequest req = new LoginRequest();
        req.setEmail(validEmail);
        String password;
        for (int i = 0; i < 10; i++) { // Worked locally with 1000 iterations
            stars();
            password = randomPass();
            req.setPassword(password);
            System.out.println("Testing: " + password);
            String loginRequest = mapper.writeValueAsString(req);
            mockMvc.perform(post(LOGIN_PATH).contentType(CONTENT_TYPE).content(loginRequest))
                    .andExpect(status().isUnauthorized())
                    .andReturn();
        }

    }
    private String randomPass() {
        String validChars = "@$!%*?&";
        String password = "";
        for (int i = 0; i < 12; i++) {
            password += "" + ThreadLocalRandom.current().nextInt(0, 9+1);
            password += "" + (char) (new Random().nextInt(26) + 'A');
            password += "" + (char) (new Random().nextInt(26) + 'a');
            password += "" + validChars.charAt(new Random().nextInt(validChars.length()));
        }
        return password;
    }

    private static void stars() { // for debugging; prints 100 asterisks
        for (int i = 0; i < 10; i++) {
            System.out.print("**********");
        }
        System.out.println();
    }
}
