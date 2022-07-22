package com.revature.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dtos.CreateProduct;
import com.revature.dtos.CreationResponse;
import com.revature.dtos.ProductInfo;
import com.revature.dtos.ReviewResponse;
import com.revature.exceptions.BadRequestException;
import com.revature.exceptions.NotFoundException;
import com.revature.exceptions.PersistanceException;
import com.revature.models.Category;
import com.revature.models.Product;
import com.revature.models.ProductReview;
import com.revature.repositories.CategoryRepository;
import com.revature.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepo;
    private final CategoryRepository categoryRepository;
    private final ObjectMapper mapper = new ObjectMapper();

    public ProductService(ProductRepository productRepo, CategoryRepository categoryRepository) {
        this.productRepo = productRepo;
        this.categoryRepository = categoryRepository;
    }

    public ResponseEntity findAll() {
        List<Product> products = productRepo.findAll();
        List<ProductInfo> prodInfo = products.stream().map(ProductInfo::new).collect(Collectors.toList());
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            int sum = 0;
            for (ProductReview rating: product.getRatings()) {
                sum += rating.getRating();
            }
            prodInfo.get(i).setSumOfRating(sum);
        }
        String resp = "";
        try {
            resp = mapper.writeValueAsString(prodInfo); // prepare JSON response
        } catch (JsonProcessingException e) {
            throw new BadRequestException();
        } // This throw is only anticipated to happen upon a bad request

        return ResponseEntity
                .status(HttpStatus.OK.value()) // Set response status
                .body(resp);        // Add the JSON response body
    }

    public ResponseEntity findReviewsByProductId(int id) {
        Product product = productRepo.findById(id)
                .orElseThrow(NotFoundException::new);

        List<ReviewResponse> reviews;
        if (product.getRatings() == null || product.getRatings().size() == 0) {
            reviews = new ArrayList<>();
        }
        else {
            reviews = product.getRatings().stream().map(ReviewResponse::new).collect(Collectors.toList());
        }
        String resp = "";
        try {
            resp = mapper.writeValueAsString(reviews); // prepare JSON response
        } catch (JsonProcessingException e) {
            throw new BadRequestException();
        } // This throw is only anticipated to happen upon a bad request

        return ResponseEntity
                .status(HttpStatus.OK.value()) // Set response status
                .body(resp);        // Add the JSON response body

    }


    public ProductInfo findById(int id){
        return productRepo.findById(id).map(ProductInfo::new).orElseThrow(NotFoundException::new);

    }

    /**
     * This method is used by the ProductController to persist a new product to the database using the ProductRepo
     * @param createProduct This is a DTO that is passed from the ProductController to this method that contains all the information from the user to be persisted
     * @return Returns a CreationResponse DTO that contains the new product ID
     */
    public CreationResponse save(CreateProduct createProduct) {
        Category category = categoryRepository.getById(createProduct.getCategory()); //This fetches the category by its Id sent in the CreateProduct DTO
        Product product = new Product(createProduct, category); //This creates a new product object with the fetched category and fields of the CreateProducts DTO
        StringBuilder errorMessage = new StringBuilder("Issue(s) with this request:");
        boolean passed = true;

        if (!categoryRepository.findById(createProduct.getCategory()).isPresent()) {
            errorMessage.append(" - No category found");
            passed = false;
        }

        if (BigDecimal.valueOf(product.getPrice()).scale() > 2) {
            errorMessage.append(" - Price too long of a decimal number");
            passed = false;
        }

        if (BigDecimal.valueOf(product.getPrice()).precision() > 8) {
            errorMessage.append(" - Price length is too long");
            passed = false;
        }

        if (product.getName().length() > 50) {
            errorMessage.append(" - Name is more then 50 characters");
            passed = false;
        }

        if (passed) {
            productRepo.save(product);
            System.out.println(categoryRepository.findById(createProduct.getCategory()).isPresent());
            return new CreationResponse(product.getProductId());

        } else{
            throw new PersistanceException(errorMessage.toString());
        }
    }

        public ResponseEntity saveAll (List < Product > productList, List < ProductInfo > metadata){
            return null;
        }

    public void delete(int id) {
    }
}
