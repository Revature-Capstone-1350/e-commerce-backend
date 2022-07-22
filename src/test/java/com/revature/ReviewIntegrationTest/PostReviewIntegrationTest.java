package com.revature.ReviewIntegrationTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dtos.LoginRequest;
import com.revature.dtos.Principal;
import com.revature.dtos.ProductReviewRequest;
import com.revature.exceptions.NotFoundException;
import com.revature.models.Product;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

//@SpringBootTest
//@DirtiesContext
//@AutoConfigureMockMvc
public class PostReviewIntegrationTest {
    private final MockMvc mockMvc;
    private final ObjectMapper jsonMapper;
    private final UserRepository userRepo;
    private final ProductRepository productService;
    private final AuthService authService;
    private final TokenService tokenService;
    private final String PATH = "/api/product/rating/";
    private final String CONTENT_TYPE = "application/json";

//    @Autowired
    public PostReviewIntegrationTest(MockMvc mockMvc, ObjectMapper jsonMapper, UserRepository userRepo, ProductRepository productService, AuthService authService, TokenService tokenService) {
        this.mockMvc = mockMvc;
        this.jsonMapper = jsonMapper;
        this.userRepo = userRepo;
        this.productService = productService;
        this.authService = authService;
        this.tokenService = tokenService;
    }

//    @Test
    void test_post_review_gives_201_with_valid_data() throws Exception {
        User user = userRepo.findByEmailIgnoreCaseAndPassword(
                "Tester1@revature.net",
                authService.generatePassword("Tester12@")
        ).orElseThrow(NotFoundException::new);
        Principal prin = new Principal(user);
        String token = tokenService.generateToken(prin);

        ProductReviewRequest reviewReq = new ProductReviewRequest(
                5,
                "Fantastic Product 'unique string here for test'"
        );

        Product product = productService.findById(3).orElseThrow(NotFoundException::new);
//        int amountOfReviews = product.getRatings().size();

        String jsonReq = jsonMapper.writeValueAsString(reviewReq);

        // now to hit the POST endpoint
        mockMvc.perform(
                post(PATH+3)
                        .contentType(CONTENT_TYPE)
                        .content(jsonReq)
                        .header("Authorization", token)
                )
                .andExpect(status().isCreated())
                .andExpect(header().string("Access-Control-Allow-Origin", "*"))
                .andExpect(header().string("Access-Control-Allow-Methods", "*"))
                .andExpect(header().string("Access-Control-Allow-Headers", "*"))
                .andReturn();

//        assertTrue(product.getRatings().contains());

    }

//    @Test
    void test_post_review_gives_400_with_invalid_data() throws Exception {
        User user = userRepo.findByEmailIgnoreCaseAndPassword(
                "Tester1@revature.net",
                authService.generatePassword("Tester12@")
        ).orElseThrow(NotFoundException::new);
        Principal prin = new Principal(user);
        String token = tokenService.generateToken(prin);

        ProductReviewRequest reviewReq = new ProductReviewRequest(
                5,
                "Fantastic Product 'unique string here for test'"
        );
        LoginRequest badReq = new LoginRequest();

        Product product = productService.findById(3).orElseThrow(NotFoundException::new);
//        int amountOfReviews = product.getRatings().size();

        String jsonReq = jsonMapper.writeValueAsString(badReq);

        // now to hit the POST endpoint
        mockMvc.perform(
                        post(PATH+3)
                                .contentType(CONTENT_TYPE)
                                .content(jsonReq)
                                .header("Authorization", token)
                )
                .andExpect(status().isBadRequest())
                .andExpect(header().string("Access-Control-Allow-Origin", "*"))
                .andExpect(header().string("Access-Control-Allow-Methods", "*"))
                .andExpect(header().string("Access-Control-Allow-Headers", "*"))
                .andReturn();

//        assertTrue(product.getRatings().contains());

    }

}
