package com.revature.services;

import com.revature.models.ProductReview;
import com.revature.repositories.ProductReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductReviewService {

    private final ProductReviewRepository productReviewRepository;

    public ProductReviewService(ProductReviewRepository productReviewRepository) {
        this.productReviewRepository = productReviewRepository;
    }

    /**
     * Makes a list of all products
     * @return a list of all products
     */
    public List<ProductReview> findAll() {
        return productReviewRepository.findAll();
    }

    /**
     * Find a product review by its id
     * @param id a integer of the id you want
     * @return Return a product review by their id
     */
    public Optional<ProductReview> findById(int id) {
        return productReviewRepository.findById(id);
    }

    /**
     * Find all users reviews by product id
     * @param id the id of the product we want
     * @return // Return a List of all reviews for that product
     */
    public List<ProductReview> findAllByProductId(int id) {
        return productReviewRepository.findByProductId(id);
    }

    /**
     * Deletes a review by its id
     * @param id the id of the review we want deleted
     */
    public void delete(int id) {
        productReviewRepository.deleteById(id);
    }
}
