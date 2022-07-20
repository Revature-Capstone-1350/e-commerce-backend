package com.revature.dtos;

import com.revature.models.ProductReview;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductReviewRequest {
    // user ID already covered
    @NotNull
    private int rating;

    @NotNull
    private String description;

    public ProductReviewRequest(ProductReview review) {
        this.rating = review.getRating();
        this.description = review.getDescription();
    }
}
