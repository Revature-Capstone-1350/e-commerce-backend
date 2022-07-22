package com.revature.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dtos.CreateProductRequest;
import com.revature.models.Category;
import com.revature.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest
//@AutoConfigureMockMvc
public class ProductCreationIntegrationTest {
    private final MockMvc mockMvc;
    private final ObjectMapper mapper;
    private final ProductRepository productRepository;
    private final String PATH = "/api/product/createproduct";
    private final String CONTENT_TYPE = "application/json";

//    @Autowired
    public ProductCreationIntegrationTest(MockMvc mockMvc, ObjectMapper mapper, ProductRepository productRepository) {
        this.mockMvc = mockMvc;
        this.mapper = mapper;
        this.productRepository = productRepository;
    }

//    @Test
    void test_product_creation_returns201givenValid() throws Exception {
        CreateProductRequest createProductRequest = new CreateProductRequest();

        createProductRequest.setName("This is a test name");
        createProductRequest.setDescription("This is a test description");
        createProductRequest.setPrice(4.99);
        createProductRequest.setImageUrlS("This is a small test image url");
        createProductRequest.setImageUrlM("This is a medium test image url");
        createProductRequest.setCategory(new Category(8));

        String requestPayload = mapper.writeValueAsString(createProductRequest);

        mockMvc.perform(post(PATH).contentType(CONTENT_TYPE).content(requestPayload))
                .andExpect(status().isCreated())
                .andExpect(header().string("Access-Control-Allow-Origin", "*"))
                .andExpect(header().string("Access-Control-Allow-Methods", "*"))
                .andExpect(header().string("Access-Control-Allow-Headers", "*"))
                .andReturn();
        assertTrue(productRepository.existsById(167));
    }
}
