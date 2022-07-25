package com.revature.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dtos.CreateProductRequest;
import com.revature.dtos.Principal;
import com.revature.models.User;
import com.revature.repositories.ProductRepository;
import com.revature.repositories.UserRepository;
import com.revature.services.AuthService;
import com.revature.services.jwt.TokenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext
public class ProductCreationIntegrationTest {
    private final MockMvc mockMvc;
    private final ObjectMapper mapper;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final TokenService tokenService;
    private final String PATH = "/api/product/createproduct";
    private final String CONTENT_TYPE = "application/json";
    final String username = "Admin@SkyView.com";
    final String password = "Admin12@";

    @Autowired
    public ProductCreationIntegrationTest(MockMvc mockMvc, ObjectMapper mapper, ProductRepository productRepository, UserRepository userRepository, AuthService authService, TokenService tokenService) {
        this.mockMvc = mockMvc;
        this.mapper = mapper;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.authService = authService;
        this.tokenService = tokenService;
    }

    @Test
    void test_product_creation_returns201_givenValid() throws Exception {
        CreateProductRequest createProductRequest = new CreateProductRequest();

        createProductRequest.setName("This is a test name");
        createProductRequest.setDescription("This is a test description");
        createProductRequest.setPrice(4.99);
        createProductRequest.setImageUrlS("This is a small test image url");
        createProductRequest.setImageUrlM("This is a medium test image url");
        createProductRequest.setCategory(8);

        User user = userRepository.findByEmailIgnoreCaseAndPassword(username, authService.generatePassword(password)).orElseThrow(RuntimeException::new);
        String token = tokenService.generateToken(new Principal(user));


        String requestPayload = mapper.writeValueAsString(createProductRequest);

        mockMvc.perform(post(PATH).contentType(CONTENT_TYPE).content(requestPayload).header("Authorization", token))
                .andExpect(status().isCreated())
                .andExpect(header().string("Access-Control-Allow-Origin", "*"))
                .andExpect(header().string("Access-Control-Allow-Methods", "*"))
                .andExpect(header().string("Access-Control-Allow-Headers", "*"))
                .andReturn();

        assertTrue(productRepository.existsById(167));
    }
    @Test
    void test_product_creation_returns400_all_fields() throws Exception {
        CreateProductRequest createProductRequest = new CreateProductRequest();

        createProductRequest.setName("012345678901234567890123456789012345678901234567890123456789");
        createProductRequest.setDescription("This is a test description");
        createProductRequest.setPrice(4.999);
        createProductRequest.setImageUrlS("This is a small test image url");
        createProductRequest.setImageUrlM("This is a medium test image url");
        createProductRequest.setCategory(9);

        User user = userRepository.findByEmailIgnoreCaseAndPassword(username, authService.generatePassword(password)).orElseThrow(RuntimeException::new);
        String token = tokenService.generateToken(new Principal(user));


        String requestPayload = mapper.writeValueAsString(createProductRequest);

        mockMvc.perform(post(PATH).contentType(CONTENT_TYPE).content(requestPayload).header("Authorization", token))
                .andExpect(status().isBadRequest())
                .andExpect(header().string("Access-Control-Allow-Origin", "*"))
                .andExpect(header().string("Access-Control-Allow-Methods", "*"))
                .andExpect(header().string("Access-Control-Allow-Headers", "*"))
                .andExpect(jsonPath("$.message").isArray())
                .andExpect(jsonPath("$.message", hasSize(1)))
                .andExpect(jsonPath("$.message", hasItem("Issue(s) with this request: - No category found - Price too long of a decimal number - Name is more then 50 characters")))
                .andReturn();
    }
    @Test
    void test_product_creation_returns400_invalid_name() throws Exception {
        CreateProductRequest createProductRequest = new CreateProductRequest();

        createProductRequest.setName("012345678901234567890123456789012345678901234567890123456789");
        createProductRequest.setDescription("This is a test description");
        createProductRequest.setPrice(4.99);
        createProductRequest.setImageUrlS("This is a small test image url");
        createProductRequest.setImageUrlM("This is a medium test image url");
        createProductRequest.setCategory(8);

        User user = userRepository.findByEmailIgnoreCaseAndPassword(username, authService.generatePassword(password)).orElseThrow(RuntimeException::new);
        String token = tokenService.generateToken(new Principal(user));


        String requestPayload = mapper.writeValueAsString(createProductRequest);

        mockMvc.perform(post(PATH).contentType(CONTENT_TYPE).content(requestPayload).header("Authorization", token))
                .andExpect(status().isBadRequest())
                .andExpect(header().string("Access-Control-Allow-Origin", "*"))
                .andExpect(header().string("Access-Control-Allow-Methods", "*"))
                .andExpect(header().string("Access-Control-Allow-Headers", "*"))
                .andExpect(jsonPath("$.message").isArray())
                .andExpect(jsonPath("$.message", hasSize(1)))
                .andExpect(jsonPath("$.message", hasItem("Issue(s) with this request: - Name is more then 50 characters")))
                .andReturn();
    }

    @Test
    void test_product_creation_returns400_invalid_price() throws Exception {
        CreateProductRequest createProductRequest = new CreateProductRequest();

        createProductRequest.setName("This is a test name");
        createProductRequest.setDescription("This is a test description");
        createProductRequest.setPrice(4.999);
        createProductRequest.setImageUrlS("This is a small test image url");
        createProductRequest.setImageUrlM("This is a medium test image url");
        createProductRequest.setCategory(8);

        User user = userRepository.findByEmailIgnoreCaseAndPassword(username, authService.generatePassword(password)).orElseThrow(RuntimeException::new);
        String token = tokenService.generateToken(new Principal(user));


        String requestPayload = mapper.writeValueAsString(createProductRequest);

        mockMvc.perform(post(PATH).contentType(CONTENT_TYPE).content(requestPayload).header("Authorization", token))
                .andExpect(status().isBadRequest())
                .andExpect(header().string("Access-Control-Allow-Origin", "*"))
                .andExpect(header().string("Access-Control-Allow-Methods", "*"))
                .andExpect(header().string("Access-Control-Allow-Headers", "*"))
                .andExpect(jsonPath("$.message").isArray())
                .andExpect(jsonPath("$.message", hasSize(1)))
                .andExpect(jsonPath("$.message", hasItem("Issue(s) with this request: - Price too long of a decimal number")))
                .andReturn();
    }

    @Test
    void test_product_creation_returns400_invalid_category() throws Exception {
        CreateProductRequest createProductRequest = new CreateProductRequest();

        createProductRequest.setName("This is a test name");
        createProductRequest.setDescription("This is a test description");
        createProductRequest.setPrice(4.99);
        createProductRequest.setImageUrlS("This is a small test image url");
        createProductRequest.setImageUrlM("This is a medium test image url");
        createProductRequest.setCategory(9);

        User user = userRepository.findByEmailIgnoreCaseAndPassword(username, authService.generatePassword(password)).orElseThrow(RuntimeException::new);
        String token = tokenService.generateToken(new Principal(user));


        String requestPayload = mapper.writeValueAsString(createProductRequest);

        mockMvc.perform(post(PATH).contentType(CONTENT_TYPE).content(requestPayload).header("Authorization", token))
                .andExpect(status().isBadRequest())
                .andExpect(header().string("Access-Control-Allow-Origin", "*"))
                .andExpect(header().string("Access-Control-Allow-Methods", "*"))
                .andExpect(header().string("Access-Control-Allow-Headers", "*"))
                .andExpect(jsonPath("$.message").isArray())
                .andExpect(jsonPath("$.message", hasSize(1)))
                .andExpect(jsonPath("$.message", hasItem("Issue(s) with this request: - No category found")))
                .andReturn();
    }
}
