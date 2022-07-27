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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext
public class DeleteProductIntegrationTest {
    private final MockMvc mockMvc;
    private final ObjectMapper mapper;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final TokenService tokenService;
    private final String CREATE_PATH = "/api/product/createproduct";
    private final String DELETE_PATH = "/api/product/deleteproduct/";
    private final String CONTENT_TYPE = "application/json";
    final String username = "Admin@SkyView.com";
    final String password = "Admin12@";

    @Autowired
    public DeleteProductIntegrationTest(MockMvc mockMvc, ObjectMapper mapper, ProductRepository productRepository, UserRepository userRepository, AuthService authService, TokenService tokenService) {
        this.mockMvc = mockMvc;
        this.mapper = mapper;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.authService = authService;
        this.tokenService = tokenService;
    }

    @Test
    void test_delete_new_product_returns_204() throws Exception {
        // First, create a new product and attach a review

        final int NEW_PRODUCT_ID = 167;

        assertFalse(productRepository.existsById(NEW_PRODUCT_ID));
        // copy+paste from ProductCreationIntegrationTest
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
        mockMvc.perform(post(CREATE_PATH).contentType(CONTENT_TYPE).content(requestPayload).header("Authorization", token))
                .andExpect(status().isCreated())
                .andReturn();

        assertTrue(productRepository.existsById(NEW_PRODUCT_ID));

        // Delete the product
        mockMvc.perform(delete(DELETE_PATH+NEW_PRODUCT_ID)
                        .header("Authorization", token))
                .andExpect(status().isNoContent())
                .andReturn();

        assertFalse(productRepository.existsById(NEW_PRODUCT_ID));
    }

    @Test
    void test_delete_old_product_returns_204() throws Exception {

        // attempt to delete a product that has orders attached to it
        final int OLD_PRODUCT_ID = 1;
        assertTrue(productRepository.existsById(OLD_PRODUCT_ID));
        User user = userRepository.findByEmailIgnoreCaseAndPassword(username, authService.generatePassword(password)).orElseThrow(RuntimeException::new);
        String token = tokenService.generateToken(new Principal(user));

        // Attempt to delete the product
        mockMvc.perform(delete(DELETE_PATH+OLD_PRODUCT_ID)
                        .header("Authorization", token))
                .andExpect(status().isNoContent())
                .andReturn();

        assertFalse(productRepository.existsById(OLD_PRODUCT_ID));
    }
}
