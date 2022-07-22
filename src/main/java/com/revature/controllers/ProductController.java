package com.revature.controllers;

import com.revature.annotations.AdminOnly;
import com.revature.dtos.CreateProduct;
import com.revature.dtos.ProductInfo;
import com.revature.dtos.ProductReviewRequest;
import com.revature.dtos.ReviewResponse;
import com.revature.exceptions.NotImplementedException;
import com.revature.models.Product;
import com.revature.services.ProductService;
import com.revature.services.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;
    private final ReviewService reviewService;

    public ProductController(ProductService productService, ReviewService reviewService) {
        this.productService = productService;
        this.reviewService = reviewService;
    }

    /**
     * This endpoint will return all the products
     * @return Return a list of the products
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity getInventory() {
        return productService.findAll();
    }


    /**
     * This endpoint will return all review for the selected product
     * @param id The id of the product we want to search
     * @return Returns all the reviews for the selected product
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/rating/{id}")
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
            @RequestHeader("Authorization") String token,
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
    @GetMapping("/{id}")
    public ProductInfo getProductById(@PathVariable("id") int id) {
        return productService.findById(id);
    }

    /**
     * Will insert product information
     * @param product
     */

    @AdminOnly
    @PutMapping
    public void insert(@RequestBody ProductInfo product) {
        throw new NotImplementedException();
    }

    /**
     * Will list product information
     * @param metadata
     * @return return product information
     */

    @AdminOnly
    @PatchMapping
    public ResponseEntity<List<ProductInfo>> purchase(@RequestBody List<ProductInfo> metadata) {
        throw new NotImplementedException();
    }

    /**
     * Will delete product by id
     * @param id
     * @return delete product
     */

    @AdminOnly
    @DeleteMapping("/{id}")
    public ResponseEntity<ProductInfo> deleteProduct(@PathVariable("id") int id) {
        throw new NotImplementedException();
    }

    /**
     * This is a POST endpoint used to create new products by sending a CreateProduct DTO to the ProductService
     * @param createProduct DTO containing all the provided details of a new product
     */
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping( value = "/createproduct", consumes = "application/json", produces = "application/json")
    public void newProduct(@RequestBody CreateProduct createProduct) {
        productService.save(createProduct);
    }
}
