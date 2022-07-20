package com.revature.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dtos.Principal;
import com.revature.dtos.ProductReviewRequest;
import com.revature.exceptions.BadRequestException;
import com.revature.exceptions.ForbiddenException;
import com.revature.models.Product;
import com.revature.models.ProductReview;
import com.revature.models.User;
import com.revature.repositories.ProductRepository;
import com.revature.repositories.ProductReviewRepository;
import com.revature.repositories.UserRepository;
import com.revature.services.jwt.TokenService;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    private final ProductRepository productRepo;
    private final ProductReviewRepository reviewRepo;
    private final UserRepository userRepo;
    private final TokenService tokenService;
    private final ObjectMapper mapper = new ObjectMapper();

    public ReviewService(ProductRepository productRepo, ProductReviewRepository reviewRepo, UserRepository userRepo, TokenService tokenService) {
        this.productRepo = productRepo;
        this.reviewRepo = reviewRepo;
        this.userRepo = userRepo;
        this.tokenService = tokenService;
    }

    public void postReview(String token, ProductReviewRequest reviewReq, int productId) {
        Principal prin = tokenService.extractTokenDetails(token);
        System.out.println(prin);
        User user = userRepo.findByUserIdAndEmailIgnoreCase(
                prin.getAuthUserId(),
                prin.getAuthUserEmail()
        ).orElseThrow(ForbiddenException::new);
        // User identity now verified

        // now, verify product exists
        Product product = productRepo.findById(productId).orElseThrow(BadRequestException::new);
        // Product exists
        ProductReview review = new ProductReview(
                reviewReq.getRating(),
                reviewReq.getDescription(),
                user,
                product
        );
        reviewRepo.save(review);
    }
}
