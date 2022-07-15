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


    /**
     * Returns a list of all reviews
     * @return A list of all reviews
     */
    @Authorized
    @GetMapping
    public ResponseEntity<List<ProductReview>> getInventory() {
        return ResponseEntity.ok(productReviewService.findAll());
    }


    /**
     * Grab review by review id
     * @param id this is the id of the review
     * @return return the review by id
     */
    @Authorized
    @GetMapping("/{id}")
    public ResponseEntity<ProductReview> getProductReviewById(@PathVariable("id") int id) {
        Optional<ProductReview> optional = productReviewService.findById(id);

        if(!optional.isPresent()) {
            return ResponseEntity.notFound().build(); // if no option return not found
        }

        return ResponseEntity.ok(optional.get()); // return the review response
    }

    /**
     *  grab all product reviews by product id
     * @param id this is product id
     * @return returns a list of all reviews for product
     */
    @Authorized
    @GetMapping("/product/{id}")
     public ResponseEntity<List<ProductReview>> getProudctReviewByProductId(@PathVariable("id")int id) {
        return ResponseEntity.ok(productReviewService.findAllByProductId(id)); // returns all reviews
    }

    /**
     * Delete review by id
     * @param id this is the review id
     * @return the review that is deleted
     */
    @Authorized
    @DeleteMapping("/{id}")
    public  ResponseEntity<ProductReview> deleteProductReview(@PathVariable("id") int id) {
        Optional<ProductReview> optional = productReviewService.findById(id);

        if(!optional.isPresent()) {
            return ResponseEntity.notFound().build(); // if no option return not found
        }

        productReviewService.delete(id); // deletes product review

        return ResponseEntity.ok(optional.get());
    }
}
