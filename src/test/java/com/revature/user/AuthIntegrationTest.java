package com.revature.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.controllers.AuthController;
import com.revature.dtos.*;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest // Tells Spring we need to have an entire application context with everything set up and ready to go
@AutoConfigureMockMvc // configures mockMvc
@DirtiesContext
class AuthIntegrationTest {

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


    @Autowired
    AuthIntegrationTest(MockMvc mockMvc, ObjectMapper mapper, UserRepository userRepo, UserRoleRepository roleRepo, AuthService authService, AuthController authCtrl, TokenService tokenService) { // Spring gives us a configured MockMvc
        this.mockMvc = mockMvc; // This is a Spring-provided object, so we wire it in
        this.mapper = mapper;
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.authService = authService;
        this.authCtrl = authCtrl;
        this.tokenService = tokenService;
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
                .andExpect(jsonPath("$.message").isArray())
                .andExpect(jsonPath("$.message", hasItem("Invalid Credentials.")))
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
        String token =
                mockMvc.perform(post(REGISTER_PATH)
                                .contentType(CONTENT_TYPE)
                                .content(registerRequest))
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

        // Third block : Utilize token to access @AdminOnly endpoint
        CreateProductRequest createProductRequest = new CreateProductRequest();
        String reqJSON = mapper.writeValueAsString(createProductRequest);


        mockMvc.perform(post(CREATE_PRODUCT_PATH)
                        .contentType(CONTENT_TYPE)
                        .content(reqJSON)
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
                .andExpect(jsonPath("$.message").isArray())
                .andExpect(jsonPath("$.message", hasItem("Access Denied")))
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.timestamp").isNotEmpty())
                .andReturn();

        // Fourth Block : Can access login-restricted endpoint

        ProductReviewRequest reviewReq = new ProductReviewRequest(5, "great picture!");
        String req = mapper.writeValueAsString(reviewReq);

        mockMvc.perform(post(POST_REVIEW_PATH)
                        .header("Authorization", token)
                        .contentType(CONTENT_TYPE)
                        .content(req)
                )
                .andExpect(status().isCreated())
                .andExpect(header().string("Access-Control-Allow-Origin", "*"))
                .andExpect(header().string("Access-Control-Allow-Methods", "*"))
                .andExpect(header().string("Access-Control-Allow-Headers", "*"))
                .andReturn();
        // This only tests authentication, not particulars of product endpoints
    }

    @Test
    void access_denied_with_bad_token() throws Exception {

        ProductReviewRequest reviewReq = new ProductReviewRequest(5, "great picture!");
        String req = mapper.writeValueAsString(reviewReq);

        final String BAD_TOKEN = "12345";

        mockMvc.perform(post(POST_REVIEW_PATH)
                        .header("Authorization", BAD_TOKEN)
                        .contentType(CONTENT_TYPE)
                        .content(req)
                )
                .andExpect(status().isBadRequest())
                .andExpect(header().string("content-type", CONTENT_TYPE))
                .andExpect(header().string("Access-Control-Allow-Origin", "*"))
                .andExpect(header().string("Access-Control-Allow-Methods", "*"))
                .andExpect(header().string("Access-Control-Allow-Headers", "*"))
                .andExpect(jsonPath("$.statusCode").exists())
                .andExpect(jsonPath("$.statusCode").isNumber())
                .andExpect(jsonPath("$.statusCode").value(400))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message").isArray())
                .andExpect(jsonPath("$.message", hasItem("Invalid Input.")))
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.timestamp").isNotEmpty())
                .andReturn();
    }

    @Test
    void test_login_returns_201_given_valid_data() throws Exception {
        LoginRequest newLoginRequest = new LoginRequest();
        newLoginRequest.setEmail(validEmail);
        newLoginRequest.setPassword(validPass);
        String loginRequest = mapper.writeValueAsString(newLoginRequest);
        assertEquals(2, roleRepo.findAll().size());
        userRepo.save(new User(
                validFirst
                , validLast
                , validEmail
                , authService.generatePassword(validPass)
                , roleRepo.getById(2)
                , null, null));
        assertTrue(userRepo.findByEmailIgnoreCaseAndPassword(
                validEmail,
                authService.generatePassword(validPass)
        ).isPresent());

        String token =
                mockMvc.perform(post(LOGIN_PATH).contentType(CONTENT_TYPE).content(loginRequest))
                        .andExpect(status().isOk())
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
        Principal prin = tokenService.extractTokenDetails(token);

    }

    @Test
    void test_register_returns_201_given_valid_data() throws Exception {
        RegisterRequest newRegistrationRequest = new RegisterRequest();
        newRegistrationRequest.setEmail(validEmail);
        newRegistrationRequest.setFirstName(validFirst);
        newRegistrationRequest.setLastName(validLast);
        newRegistrationRequest.setPassword(validPass);
        String registerRequest = mapper.writeValueAsString(newRegistrationRequest);
        assertFalse(userRepo.existsByEmailIgnoreCase(validEmail));
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

        Principal prin = tokenService.extractTokenDetails(token);
        assertTrue(prin.getAuthUserEmail().equalsIgnoreCase(validEmail));
        assertEquals(prin.getAuthUserId(), (int) user.getUserId());
    }

    @Test
    void test_login_dto_no_number() throws Exception {
        LoginRequest req = new LoginRequest();
        req.setEmail(validEmail);
        req.setPassword("aA@@@@@@");
        String loginRequest = mapper.writeValueAsString(req);

        final String NUMBER_MISSING_CHECK = "Password must contain at least one number";
        assertEquals(PASSWORD_NUMBER, NUMBER_MISSING_CHECK);

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
                .andExpect(jsonPath("$.message").isArray())
                .andExpect(jsonPath("$.message", hasItem(PASSWORD_NUMBER)))
                .andExpect(jsonPath("$.message", hasSize(1)))
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

        final String LOWER_MISSING_CHECK = "Password must contain at least one lowercase letter";
        assertEquals(PASSWORD_LOWER, LOWER_MISSING_CHECK);

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
                .andExpect(jsonPath("$.message").isArray())
                .andExpect(jsonPath("$.message", hasItem(PASSWORD_LOWER)))
                .andExpect(jsonPath("$.message", hasSize(1)))
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

        final String UPPER_MISSING_CHECK = "Password must contain at least one uppercase letter";
        assertEquals(PASSWORD_UPPER, UPPER_MISSING_CHECK);

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
                .andExpect(jsonPath("$.message").isArray())
                .andExpect(jsonPath("$.message", hasItem(PASSWORD_UPPER)))
                .andExpect(jsonPath("$.message", hasSize(1)))
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

        final String SYMBOL_MISSING_CHECK = "Password must contain at least one of @$!%*?&";
        assertEquals(PASSWORD_SYMBOL, SYMBOL_MISSING_CHECK);

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
                .andExpect(jsonPath("$.message").isArray())
                .andExpect(jsonPath("$.message", hasItem(PASSWORD_SYMBOL)))
                .andExpect(jsonPath("$.message", hasSize(1)))
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

        final String LENGTH_CHECK = "Password must be at least 8 characters";
        assertEquals(PASSWORD_LENGTH, LENGTH_CHECK);

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
                .andExpect(jsonPath("$.message").isArray())
                .andExpect(jsonPath("$.message", hasItem(PASSWORD_LENGTH)))
                .andExpect(jsonPath("$.message", hasSize(1)))
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
                .andExpect(jsonPath("$.message").isArray())
                .andExpect(jsonPath("$.message", hasItem("Invalid Credentials.")))
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
