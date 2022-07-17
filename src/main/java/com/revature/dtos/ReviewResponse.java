package com.revature.dtos;

import com.revature.models.ProductReview;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReviewResponse {
    private int id;
    private int rating;
    private String description;
    private int reviewerId;
    private String reviewerName;
    private int productId;

    public ReviewResponse(ProductReview review) {
        this.id = review.getProductReviewId();
        this.rating = review.getRating();
        this.description = review.getDescription();
        this.reviewerId = review.getUser().getUserId();
        this.reviewerName = "";
        String nameBuilder = "";
        if (review.getUser().getFirstName() != null
        && !review.getUser().getFirstName().isEmpty()) {
            nameBuilder += review.getUser().getFirstName();
        }
        if (review.getUser().getLastName() != null
        && !review.getUser().getFirstName().isEmpty()) {
            if (!nameBuilder.isEmpty()) nameBuilder += " ";
            nameBuilder +=review.getUser().getLastName();
        }
        this.reviewerName = nameBuilder;
        this.productId = review.getProduct().getProductId();
    }
}
