//package com.revature.services;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.revature.ECommerceApplication;
//import com.revature.dtos.CreateProductRequest;
//import com.revature.dtos.ProductInfo;
//import com.revature.exceptions.BadRequestException;
//import com.revature.models.Category;
//import com.revature.models.Product;
//import com.revature.repositories.CategoryRepository;
//import com.revature.repositories.ProductRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//
//import javax.transaction.Transactional;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.*;
//
//@Transactional
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ECommerceApplication.class)
//class ProductServiceTest {
//
//    private ProductService sut; // SUT: System Under Test
//    private ProductRepository mockProductRepository = mock(ProductRepository.class);
//
//    private final CategoryRepository mockCategoryRepository = mock(CategoryRepository.class);
//
//
//
//    @BeforeEach
//    public void setup() {
//        reset(mockProductRepository);
//        sut = new ProductService(mockProductRepository, mockCategoryRepository);
//    }
//
//    @Test
//    void test_findAll_returnsListOfProduct_providedRepositoryReturnsProducts() {
//
//        //  Given:  requested
//        Category category = new Category("Sun");
//        List<Product> mockProducts = Arrays.asList(
//                new Product(category, "name 1", "description-1", 10.00, "image-1", "image-11"),
//                new Product(category, "name 2", "description-2", 20.00, "image-2", "image-22"),
//                new Product(category, "name 3", "description-3", 30.00, "image-3", "image-33"),
//                new Product(category, "name 4", "description-4", 40.00, "image-4", "image-44"),
//                new Product(category, "name 5", "description-5", 50.00, "image-5", "image-55"),
//                new Product(category, "name 6", "description-6", 60.00, "image-6", "image-66")
//        );
//
//        when(mockProductRepository.findAll()).thenReturn(mockProducts); //  here we invoke the findAll method for testing.
//
//        //  When:   response is actual or true
//        List<ProductInfo> actual = sut.findAll();
//
//        //  Then: verify
//        assertEquals(mockProducts.size(), actual.size());
//    }
//
//    @Test
//    void checkIfFoundById() {
//
//        assertEquals(0, sut.findAll().size());
//
//        //  Given:  requested
//        Category category = new Category("Sun");
//        Product mockProduct = new Product(category, "name 1", "description-1", 10.00, "image-1", "image-11");
//        System.out.println(mockProduct);
//        CreateProductRequest mockProductCreateRequest = new CreateProductRequest(
//                mockProduct.getName(),
//                mockProduct.getDescription(),
//                mockProduct.getPrice(),
//                mockProduct.getImageUrlS(),
//                mockProduct.getImageUrlM(),
//                mockProduct.getCategory(),0
//        );
//        System.out.println(mockProductCreateRequest);
//        sut.save(mockProductCreateRequest);
//
//        //  When:   response is actual or true
//        ProductInfo actual = sut.findById(1);
//
//        //  Then: verify
//        //assertThat(mockProduts.size), actual.size());
//    }
//}