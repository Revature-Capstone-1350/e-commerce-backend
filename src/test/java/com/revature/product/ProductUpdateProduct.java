package com.revature.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dtos.ProductInfo;
import com.revature.dtos.ProductRequest;
import com.revature.models.Category;
import com.revature.models.Product;
import com.revature.repositories.CategoryRepository;
import com.revature.repositories.ProductRepository;
import com.revature.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ProductUpdateProduct {

    private final MockMvc mockMvc;
    private final ObjectMapper mapper;
    private final ProductService productService;
    private final CategoryRepository categoryRepo;
    private final String PATH = "/api/product";
    private final String CONTENT_TYPE = "application/json";

    @Autowired
    public ProductUpdateProduct(MockMvc mockMvc, ObjectMapper mapper, ProductService productService, CategoryRepository categoryRepo) {
        this.mockMvc = mockMvc;
        this.mapper = mapper;
        this.productService = productService;
        this.categoryRepo = categoryRepo;
    }

    @Test
    public void test_updating_product_204() throws Exception {
        ProductRequest updateProduct = new ProductRequest(
                1,
                "product name",
                "product description",
                1234.12,
                "URLSmall",
                "URLMedium",
                1
                );

        String json = mapper.writeValueAsString(updateProduct);

        mockMvc.perform(put(PATH).contentType(CONTENT_TYPE).content(json))
                .andExpect(status().isNoContent())
                .andExpect(header().string("Access-Control-Allow-Origin", "*"))
                .andExpect(header().string("Access-Control-Allow-Methods", "*"))
                .andExpect(header().string("Access-Control-Allow-Headers", "*"))
                .andReturn();

        ProductInfo test = productService.findById(updateProduct.getId());
        Category testCat = categoryRepo.getById(updateProduct.getCategory());

        Assertions.assertEquals(updateProduct.getId(), test.getProductId());
        Assertions.assertEquals(updateProduct.getName(), test.getName());
        Assertions.assertEquals(updateProduct.getDescription(), test.getDescription());
        Assertions.assertEquals(updateProduct.getPrice(), test.getPrice());
        Assertions.assertEquals(updateProduct.getImageUrlS(), test.getImgUrlSmall());
        Assertions.assertEquals(updateProduct.getImageUrlM(), test.getImgUrlMed());
        Assertions.assertEquals(testCat.getName(), test.getCategory());
    }

    @Test
    public void test_updating_product_422_all_fields() throws Exception {
        ProductRequest updateProduct = new ProductRequest(
                -1,
                "012345678901234567890123456789012345678901234567890123456789",
                "product description",
                1234567.123,
                "URLSmall",
                "URLMedium",
                -1
        );

        String json = mapper.writeValueAsString(updateProduct);

        MvcResult result = mockMvc.perform(put(PATH).contentType(CONTENT_TYPE).content(json))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(header().string("Access-Control-Allow-Origin", "*"))
                .andExpect(header().string("Access-Control-Allow-Methods", "*"))
                .andExpect(header().string("Access-Control-Allow-Headers", "*"))
                .andExpect(jsonPath("$.message").isArray())
                .andExpect(jsonPath("$.message", hasSize(1)))
                .andExpect(jsonPath("$.message", hasItem("Issue(s) with this request: - No product found for this id - No category found - Price too long of a decimal number - Price length is too long - Name is more then 50 characters")))
                .andReturn();

    }

    @Test
    public void test_updating_product_422_id() throws Exception {
        ProductRequest updateProduct = new ProductRequest(
                -1,
                "0123456789",
                "product description",
                1234.12,
                "URLSmall",
                "URLMedium",
                1
        );

        String json = mapper.writeValueAsString(updateProduct);

        MvcResult result = mockMvc.perform(put(PATH).contentType(CONTENT_TYPE).content(json))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(header().string("Access-Control-Allow-Origin", "*"))
                .andExpect(header().string("Access-Control-Allow-Methods", "*"))
                .andExpect(header().string("Access-Control-Allow-Headers", "*"))
                .andExpect(jsonPath("$.message").isArray())
                .andExpect(jsonPath("$.message", hasSize(1)))
                .andExpect(jsonPath("$.message", hasItem("Issue(s) with this request: - No product found for this id")))
                .andReturn();

    }

    @Test
    public void test_updating_product_422_name() throws Exception {
        ProductRequest updateProduct = new ProductRequest(
                1,
                "012345678901234567890123456789012345678901234567890123456789",
                "product description",
                1234.12,
                "URLSmall",
                "URLMedium",
                1
        );

        String json = mapper.writeValueAsString(updateProduct);

        MvcResult result = mockMvc.perform(put(PATH).contentType(CONTENT_TYPE).content(json))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(header().string("Access-Control-Allow-Origin", "*"))
                .andExpect(header().string("Access-Control-Allow-Methods", "*"))
                .andExpect(header().string("Access-Control-Allow-Headers", "*"))
                .andExpect(jsonPath("$.message").isArray())
                .andExpect(jsonPath("$.message", hasSize(1)))
                .andExpect(jsonPath("$.message", hasItem("Issue(s) with this request: - Name is more then 50 characters")))
                .andReturn();

    }

    @Test
    public void test_updating_product_422_price_scale() throws Exception {
        ProductRequest updateProduct = new ProductRequest(
                1,
                "01234567890",
                "product description",
                1234.123,
                "URLSmall",
                "URLMedium",
                1
        );

        String json = mapper.writeValueAsString(updateProduct);

        MvcResult result = mockMvc.perform(put(PATH).contentType(CONTENT_TYPE).content(json))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(header().string("Access-Control-Allow-Origin", "*"))
                .andExpect(header().string("Access-Control-Allow-Methods", "*"))
                .andExpect(header().string("Access-Control-Allow-Headers", "*"))
                .andExpect(jsonPath("$.message").isArray())
                .andExpect(jsonPath("$.message", hasSize(1)))
                .andExpect(jsonPath("$.message", hasItem("Issue(s) with this request: - Price too long of a decimal number")))
                .andReturn();

    }

    @Test
    public void test_updating_product_422_price_precision() throws Exception {
        ProductRequest updateProduct = new ProductRequest(
                1,
                "0123456789",
                "product description",
                123456789.12,
                "URLSmall",
                "URLMedium",
                1
        );

        String json = mapper.writeValueAsString(updateProduct);

        MvcResult result = mockMvc.perform(put(PATH).contentType(CONTENT_TYPE).content(json))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(header().string("Access-Control-Allow-Origin", "*"))
                .andExpect(header().string("Access-Control-Allow-Methods", "*"))
                .andExpect(header().string("Access-Control-Allow-Headers", "*"))
                .andExpect(jsonPath("$.message").isArray())
                .andExpect(jsonPath("$.message", hasSize(1)))
                .andExpect(jsonPath("$.message", hasItem("Issue(s) with this request: - Price length is too long")))
                .andReturn();

    }

    @Test
    public void test_updating_product_422_category() throws Exception {
        ProductRequest updateProduct = new ProductRequest(
                1,
                "0123456789",
                "product description",
                1234.12,
                "URLSmall",
                "URLMedium",
                -1
        );

        String json = mapper.writeValueAsString(updateProduct);

        MvcResult result = mockMvc.perform(put(PATH).contentType(CONTENT_TYPE).content(json))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(header().string("Access-Control-Allow-Origin", "*"))
                .andExpect(header().string("Access-Control-Allow-Methods", "*"))
                .andExpect(header().string("Access-Control-Allow-Headers", "*"))
                .andExpect(jsonPath("$.message").isArray())
                .andExpect(jsonPath("$.message", hasSize(1)))
                .andExpect(jsonPath("$.message", hasItem("Issue(s) with this request: - No category found")))
                .andReturn();

    }

}
