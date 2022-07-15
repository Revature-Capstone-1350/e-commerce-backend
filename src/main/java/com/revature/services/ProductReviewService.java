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

    public List<ProductReview> findAll() {
        return productReviewRepository.findAll(); // Return a list of all reviews
    }

    public Optional<ProductReview> findById(int id) {
        return productReviewRepository.findById(id); // Return a product review by their id
    }

    public List<ProductReview> findAllByProductId(int id) {
        return productReviewRepository.findByProductId(id); // Return a List of all reviews for that product
    }

    public void delete(int id) {
        productReviewRepository.deleteById(id);
    } // delete product review by id
}
