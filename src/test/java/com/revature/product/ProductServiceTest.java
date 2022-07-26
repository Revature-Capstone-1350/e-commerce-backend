package com.revature.product;

import com.revature.ECommerceApplication;
import com.revature.dtos.CreateProductRequest;
import com.revature.dtos.ProductInfo;
import com.revature.dtos.ProductRequest;
import com.revature.exceptions.PersistanceException;
import com.revature.exceptions.UnprocessableEntityException;
import com.revature.models.Category;
import com.revature.models.Product;
import com.revature.repositories.CategoryRepository;
import com.revature.repositories.ProductRepository;
import com.revature.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ECommerceApplication.class)
public class ProductServiceTest {

    @Autowired
    private final ProductService productService;

    @Autowired
    private final CategoryRepository categoryRepo;
    ProductRepository productRepo;

    private ProductService sut; // SUT: System Under Test
    private ProductRepository mockProductRepository = mock(ProductRepository.class);

    private final CategoryRepository mockCategoryRepository = mock(CategoryRepository.class);

    @Autowired
    ProductServiceTest(ProductRepository productRepo, CategoryRepository categoryRepo) {
        this.productService = new ProductService(productRepo, categoryRepo);
        this.categoryRepo = categoryRepo;
        this.productRepo = productRepo;
    }

    @BeforeEach
    public void setup() {
        reset(mockProductRepository);
        sut = new ProductService(mockProductRepository, mockCategoryRepository);
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

        UnprocessableEntityException thrown = Assertions.assertThrows(UnprocessableEntityException.class, () -> {
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

        UnprocessableEntityException thrown = Assertions.assertThrows(UnprocessableEntityException.class, () -> {
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

        UnprocessableEntityException thrown = Assertions.assertThrows(UnprocessableEntityException.class, () -> {
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

        UnprocessableEntityException thrown = Assertions.assertThrows(UnprocessableEntityException.class, () -> {
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

        UnprocessableEntityException thrown = Assertions.assertThrows(UnprocessableEntityException.class, () -> {
            productService.updateProduct(requestProduct);
        });

        Assertions.assertEquals("Issue(s) with this request: - No category found", thrown.getMessage());

    }

    @Test
    void test_findAll_returnsListOfProduct_providedRepositoryReturnsProducts() {

        //  Given:  requested
        Category category = new Category("Sun");
        List<Product> mockProducts = Arrays.asList(
                new Product(category, "name 1", "description-1", 10.00, "image-1", "image-11"),
                new Product(category, "name 2", "description-2", 20.00, "image-2", "image-22"),
                new Product(category, "name 3", "description-3", 30.00, "image-3", "image-33"),
                new Product(category, "name 4", "description-4", 40.00, "image-4", "image-44"),
                new Product(category, "name 5", "description-5", 50.00, "image-5", "image-55"),
                new Product(category, "name 6", "description-6", 60.00, "image-6", "image-66")
        );

        when(mockProductRepository.findAll()).thenReturn(mockProducts); //  here we invoke the findAll method for testing.

        //  When:   response is actual or tru
        List<ProductInfo> actual = sut.findAll();

        //  Then: verify
        assertEquals(mockProducts.size(), actual.size());
    }


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
