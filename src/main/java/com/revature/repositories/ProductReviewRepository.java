package com.revature.repositories;

import com.revature.models.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductReviewRepository extends JpaRepository<ProductReview, Integer> {
    List<ProductReview> findByProductId(int id); //Get the product id so we can search for all reviews for product.
}
