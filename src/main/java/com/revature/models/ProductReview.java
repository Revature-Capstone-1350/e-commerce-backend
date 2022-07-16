package com.revature.models;

import javax.persistence.*;

@Entity
@Table(name = "product_reviews")
public class ProductReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_review_id", updatable = false, nullable = false)
    private Integer productReviewId;

    @Column(nullable = false)
    private Integer rating;

    @Column(length=500, nullable = false) // TODO : length=500 ?
    private String description;

    @ManyToOne // one user, many ratings; each rating has one associated user
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    @ManyToOne // one user, many ratings; each rating has one associated user
    @JoinColumn(name = "product_id", nullable = false)
    private Product productId;

    public ProductReview() { super(); }

    public ProductReview(Integer productReviewId, Integer rating, String description, User userId, Product productId) {
        this();
        this.productReviewId = productReviewId;
        this.rating = rating;
        this.description = description;
        this.userId = userId;
        this.productId = productId;
    }

    public Integer getProductReviewId() { return productReviewId; }
    public Integer getRating() { return rating; }
    public String getDescription() { return description; }
    public User getUserId() { return userId; }
    public Product getProductId() { return productId; }

    public void setProductReviewId(Integer productReviewId) { this.productReviewId = productReviewId; }
    public void setRating(Integer rating) { this.rating = rating; }
    public void setDescription(String description) { this.description = description; }
    public void setUserId(User userId) { this.userId = userId; }
    public void setProductId(Product productId) { this.productId = productId; }
}
