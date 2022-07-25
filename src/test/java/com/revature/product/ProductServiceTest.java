package com.revature.product;

import com.revature.ECommerceApplication;
import com.revature.dtos.CreateProductRequest;
import com.revature.dtos.ProductInfo;
import com.revature.dtos.ProductRequest;
import com.revature.exceptions.PersistanceException;
import com.revature.exceptions.UnprocessableEntityException;
import com.revature.models.Category;
import com.revature.repositories.CategoryRepository;
import com.revature.repositories.ProductRepository;
import com.revature.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ECommerceApplication.class)
public class ProductServiceTest {

    private final ProductService productService;
    private final CategoryRepository categoryRepo;
    ProductRepository productRepo;

    @Autowired
    ProductServiceTest(ProductRepository productRepo, CategoryRepository categoryRepo) {
        this.productService = new ProductService(productRepo, categoryRepo);
        this.categoryRepo = categoryRepo;
        this.productRepo = productRepo;
    }

    @Test
    void update_pass() {
        ProductRequest requestProduct = new ProductRequest(
                1,
                "0123456789",
                "0123456789",
                1234.12,
                "SmallURL",
                "MediumURL",
                1
        );
        productService.updateProduct(requestProduct);
        ProductInfo updatedProduct = productService.findById(requestProduct.getId());
        Category updateCategory = categoryRepo.getById(requestProduct.getCategory());

        Assertions.assertEquals(requestProduct.getId(), updatedProduct.getProductId());
        Assertions.assertEquals(requestProduct.getName(), updatedProduct.getName());
        Assertions.assertEquals(requestProduct.getDescription(), updatedProduct.getDescription());
        Assertions.assertEquals(requestProduct.getPrice(), updatedProduct.getPrice());
        Assertions.assertEquals(requestProduct.getImageUrlS(), updatedProduct.getImgUrlSmall());
        Assertions.assertEquals(requestProduct.getImageUrlM(), updatedProduct.getImgUrlMed());
        Assertions.assertEquals(updateCategory.getName(), updatedProduct.getCategory());
    }

    @Test
    void update_product_bad_id() {
        ProductRequest requestProduct = new ProductRequest(
                -1,
                "0123456789",
                "0123456789",
                1234.12,
                "SmallURL",
                "MediumURL",
                1
        );

        UnprocessableEntityException thrown = Assertions.assertThrows(UnprocessableEntityException.class, ()-> {
            productService.updateProduct(requestProduct);
        });

        Assertions.assertEquals("Issue(s) with this request: - No product found for this id", thrown.getMessage());

    }

    @Test
    void update_product_long_name() {
        ProductRequest requestProduct = new ProductRequest(
                2,
                "012345678901234567890123456789012345678901234567890123456789",
                "0123456789",
                1234.12,
                "SmallURL",
                "MediumURL",
                1
        );

        UnprocessableEntityException thrown = Assertions.assertThrows(UnprocessableEntityException.class, ()-> {
            productService.updateProduct(requestProduct);
        });

        Assertions.assertEquals("Issue(s) with this request: - Name is more then 50 characters", thrown.getMessage());

    }

    @Test
    void update_product_price_decimal_too_long() {
        ProductRequest requestProduct = new ProductRequest(
                2,
                "0123456789",
                "0123456789",
                1234.1234,
                "SmallURL",
                "MediumURL",
                1
        );

        UnprocessableEntityException thrown = Assertions.assertThrows(UnprocessableEntityException.class, ()-> {
            productService.updateProduct(requestProduct);
        });

        Assertions.assertEquals("Issue(s) with this request: - Price too long of a decimal number", thrown.getMessage());

    }

    @Test
    void update_product_price_length_too_long() {
        ProductRequest requestProduct = new ProductRequest(
                2,
                "0123456789",
                "0123456789",
                12345678.12,
                "SmallURL",
                "MediumURL",
                1
        );

        UnprocessableEntityException thrown = Assertions.assertThrows(UnprocessableEntityException.class, ()-> {
            productService.updateProduct(requestProduct);
        });

        Assertions.assertEquals("Issue(s) with this request: - Price length is too long", thrown.getMessage());

    }

    @Test
    void update_product_price_bad_category() {
        ProductRequest requestProduct = new ProductRequest(
                2,
                "0123456789",
                "0123456789",
                1234.12,
                "SmallURL",
                "MediumURL",
                -1
        );

        UnprocessableEntityException thrown = Assertions.assertThrows(UnprocessableEntityException.class, ()-> {
            productService.updateProduct(requestProduct);
        });

        Assertions.assertEquals("Issue(s) with this request: - No category found", thrown.getMessage());

    }

    @Test
    void create_product_pass() {
        CreateProductRequest createProductRequest = new CreateProductRequest();

        createProductRequest.setName("This is a test name");
        createProductRequest.setDescription("This is a test description");
        createProductRequest.setPrice(4.99);
        createProductRequest.setImageUrlS("This is a small test image url");
        createProductRequest.setImageUrlM("This is a medium test image url");
        createProductRequest.setCategory(8);

        productService.save(createProductRequest);

        Assertions.assertTrue(productRepo.existsById(167));
    }

    @Test
    void create_product_long_name() {
        CreateProductRequest createProductRequest = new CreateProductRequest();

        createProductRequest.setName("012345678901234567890123456789012345678901234567890123456789");
        createProductRequest.setDescription("This is a test description");
        createProductRequest.setPrice(4.99);
        createProductRequest.setImageUrlS("This is a small test image url");
        createProductRequest.setImageUrlM("This is a medium test image url");
        createProductRequest.setCategory(8);

        PersistanceException thrown = Assertions.assertThrows(PersistanceException.class, ()-> {
            productService.save(createProductRequest);
        });
        Assertions.assertEquals("Issue(s) with this request: - Name is more then 50 characters", thrown.getMessage());
    }

    @Test
    void create_product_decimal_too_long() {
        CreateProductRequest createProductRequest = new CreateProductRequest();

        createProductRequest.setName("This is a test name");
        createProductRequest.setDescription("This is a test description");
        createProductRequest.setPrice(4.999);
        createProductRequest.setImageUrlS("This is a small test image url");
        createProductRequest.setImageUrlM("This is a medium test image url");
        createProductRequest.setCategory(8);

        PersistanceException thrown = Assertions.assertThrows(PersistanceException.class, () -> {
            productService.save(createProductRequest);
        });
        Assertions.assertEquals("Issue(s) with this request: - Price too long of a decimal number", thrown.getMessage());
    }

    @Test
    void create_product_price_length_too_long() {
        CreateProductRequest createProductRequest = new CreateProductRequest();

        createProductRequest.setName("This is a test name");
        createProductRequest.setDescription("This is a test description");
        createProductRequest.setPrice(12345678.12);
        createProductRequest.setImageUrlS("This is a small test image url");
        createProductRequest.setImageUrlM("This is a medium test image url");
        createProductRequest.setCategory(8);

        PersistanceException thrown = Assertions.assertThrows(PersistanceException.class, () -> {
            productService.save(createProductRequest);
        });
        Assertions.assertEquals("Issue(s) with this request: - Price length is too long", thrown.getMessage());
    }

    @Test
    void create_product_invalid_category() {
        CreateProductRequest createProductRequest = new CreateProductRequest();

        createProductRequest.setName("This is a test name");
        createProductRequest.setDescription("This is a test description");
        createProductRequest.setPrice(4.99);
        createProductRequest.setImageUrlS("This is a small test image url");
        createProductRequest.setImageUrlM("This is a medium test image url");
        createProductRequest.setCategory(-1);

        PersistanceException thrown = Assertions.assertThrows(PersistanceException.class, () -> {
            productService.save(createProductRequest);
        });
        Assertions.assertEquals("Issue(s) with this request: - No category found", thrown.getMessage());
    }
}
