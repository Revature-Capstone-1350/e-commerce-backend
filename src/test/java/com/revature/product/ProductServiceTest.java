package com.revature.product;

import com.revature.ECommerceApplication;
import com.revature.dtos.ProductInfo;
import com.revature.dtos.ProductRequest;
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

    @Autowired
    ProductServiceTest(ProductRepository productRepo, CategoryRepository categoryRepo) {
        this.productService = new ProductService(productRepo, categoryRepo);
        this.categoryRepo = categoryRepo;
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


}
