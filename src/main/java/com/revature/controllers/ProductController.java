package com.revature.controllers;

import com.revature.annotations.AdminOnly;
import com.revature.dtos.CreateProductRequest;
import com.revature.dtos.ProductInfo;
import com.revature.dtos.ProductRequest;
import com.revature.dtos.ProductReviewRequest;
import com.revature.dtos.ReviewResponse;
import com.revature.services.ProductService;
import com.revature.services.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;
    private final ReviewService reviewService;
    private static final String AUTHORIZATION = "Authorization";

    public ProductController(ProductService productService, ReviewService reviewService) {
        this.productService = productService;
        this.reviewService = reviewService;
    }

    /**
     * This endpoint will return all the products
     * @return Return a list of the products
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = "application/json")
    public List<ProductInfo> getInventory() {
        return productService.findAll();
    }


    /**
     * This endpoint will return all review for the selected product
     * @param id The id of the product we want to search
     * @return Returns all the reviews for the selected product
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(
            path = "/rating/{id}",
            produces = "application/json"
    )
    public List<ReviewResponse> getProductReviewsById(@PathVariable("id") int id) {
        return productService.findReviewsByProductId(id);
    }

    /**
     *
     * @param token The token needed once a person is logged in
     * @param reviewReq The review information passed into the review
     * @param productId The id of the product
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(
            path = "/rating/{productId}",
            consumes = "application/json"
    )
    public void addReview(
            @RequestHeader(AUTHORIZATION) String token,
            @RequestBody @Valid ProductReviewRequest reviewReq,
            @PathVariable("productId") int productId
    ) {
        reviewService.postReview(token, reviewReq, productId);
    }

    /**
     * Will return product information by product id
     * @param id
     * @return return product
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}", produces = "application/json")
    public ProductInfo getProductById(@PathVariable("id") int id) {
        return productService.findById(id);
    }


    /**
     * put endpoint to update a product
     * @param product receives a product Json
     */
    @AdminOnly
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(consumes = "application/json")
    public void update(@RequestBody ProductRequest product) {
        /*
        //example json
        {
            "id":1,
            "name":"limit 50 char",
            "description":"no limit",
            "price":123456.12,
            "imageUrlS":"url",
            "imageUrlM":"url",
            "category":1
        }
        */
        productService.updateProduct(product);
    }

    /**
     *  POST endpoint that utilizes the ProductService to persist a new product to the database
     * @param createProductRequest DTO that maps to the product model
     */
    @AdminOnly
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/createproduct", consumes = "application/json")
    public void newProduct(
            @RequestHeader(AUTHORIZATION) String token, // for AOP validation
            @RequestBody CreateProductRequest createProductRequest
    )
    {
        productService.save(createProductRequest);
    }
}
