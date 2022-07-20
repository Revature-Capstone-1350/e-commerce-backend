package com.revature.controllers;

import com.revature.annotations.AdminOnly;
import com.revature.dtos.ProductInfo;
import com.revature.dtos.ProductReviewRequest;
import com.revature.exceptions.NotImplementedException;
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

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity getInventory() {
        return productService.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/rating/{id}")
    public ResponseEntity getProductReviewsById(@PathVariable("id") int id) {
        return productService.findReviewsByProductId(id);
    }

    /**
     *
     * @param token
     * @param reviewReq
     * @param productId
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

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ResponseEntity getProductById(@PathVariable("id") int id) {
        throw new NotImplementedException();
    }

    @AdminOnly
    @PutMapping
    public void insert(@RequestBody ProductInfo product) {
        throw new NotImplementedException();
    }

    @AdminOnly
    @PatchMapping
    public ResponseEntity<List<ProductInfo>> purchase(@RequestBody List<ProductInfo> metadata) {
        throw new NotImplementedException();
    }

    @AdminOnly
    @DeleteMapping("/{id}")
    public ResponseEntity<ProductInfo> deleteProduct(@PathVariable("id") int id) {
        throw new NotImplementedException();
    }
}
