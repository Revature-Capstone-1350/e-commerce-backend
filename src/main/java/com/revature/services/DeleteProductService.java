package com.revature.services;

import com.revature.exceptions.BadRequestException;
import com.revature.exceptions.PersistanceException;
import com.revature.models.Product;
import com.revature.models.ProductReview;
import com.revature.repositories.CategoryRepository;
import com.revature.repositories.ProductRepository;
import com.revature.repositories.ProductReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeleteProductService { // Added as separate service to avoid refactoring tests

    private final ProductRepository productRepo;
    private final CategoryRepository categoryRepo;
    private final ProductReviewRepository reviewRepo;

    @Autowired
    public DeleteProductService(ProductRepository productRepo, CategoryRepository categoryRepo, ProductReviewRepository reviewRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
        this.reviewRepo = reviewRepo;
    }

    public void deleteProduct(int productId) {
        try {
            Product product = productRepo.findById(productId)
                    .orElseThrow(BadRequestException::new);
            List<ProductReview> reviews = product.getRatings();
            if (reviews != null && !reviews.isEmpty()) {
                for (ProductReview review: reviews) { // Review has no dependencies
                    reviewRepo.delete(review);
                }
            }
            productRepo.deleteById(productId);
        } catch (Exception e) {
            throw new PersistanceException("There are orders associated with this product.");
        }

    }
}
