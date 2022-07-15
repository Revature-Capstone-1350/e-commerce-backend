package com.revature.controllers;

import com.revature.annotations.Authorized;
import com.revature.models.ProductReview;
import com.revature.services.ProductReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product/review")
public class ProductReviewController {

    private final ProductReviewService productReviewService;

    public ProductReviewController(ProductReviewService productReviewService) {
        this.productReviewService = productReviewService;
    }

    @Authorized
    @GetMapping
    public ResponseEntity<List<ProductReview>> getInventory() {
        return ResponseEntity.ok(productReviewService.findAll());
    }

    @Authorized
    @GetMapping("/{id}")
    public ResponseEntity<ProductReview> getProductReviewById(@PathVariable("id") int id) {
        Optional<ProductReview> optional = productReviewService.findById(id);

        if(!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(optional.get());
    }

    @Authorized
    @GetMapping("/product/{id}")
     public ResponseEntity<List<ProductReview>> getProudctReviewByProductId(@PathVariable("id")int id) {
        return ResponseEntity.ok(productReviewService.findAllByProductId(id));
    }

    @Authorized
    @DeleteMapping("/{id}")
    public  ResponseEntity<ProductReview> deleteProductReview(@PathVariable("id") int id) {
        Optional<ProductReview> optional = productReviewService.findById(id);

        if(!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        productReviewService.delete(id);

        return ResponseEntity.ok(optional.get());
    }
}
