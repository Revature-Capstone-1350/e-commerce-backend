package com.revature.models;

import javax.persistence.*;

@Entity // necessary annotation to mark this class as an entity bean
@Table(name = "`product_review`") // specifies database table name
public class ProductReview {

    @Id // Identifies this column as a primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-incrementing
    @Column(name = "product_review_id", // defines column name in the database
            insertable = false, // this column is not included in generated INSERT statements
            updatable = false,  // this column is not included in generated UPDATE statements
            nullable = false
            )
    private Integer productReviewId;

    @Column(nullable = false)
    private Integer rating;

    @Column(length = 500, nullable = false) // TODO : length=500 ?
    private String description;

    @ManyToOne // one user, many ratings; each rating has one associated user
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    @ManyToOne // one user, many ratings; each rating has one associated user
    @JoinColumn(name = "product_id", nullable = false)
    private Product productId;

    // constructors
    public ProductReview() { super(); } // required no-args constructor

    public ProductReview(Integer productReviewId, Integer rating, String description, User userId, Product productId) {
        this();
        this.productReviewId = productReviewId;
        this.rating = rating;
        this.description = description;
        this.userId = userId;
        this.productId = productId;
    }

    // getters
    public Integer getProductReviewId() { return productReviewId; }
    public Integer getRating() { return rating; }
    public String getDescription() { return description; }
    public User getUserId() { return userId; }
    public Product getProductId() { return productId; }

    // setters
//    public void setProductReviewId(Integer productReviewId) { this.productReviewId = productReviewId; }
    public void setRating(Integer rating) { this.rating = rating; }
    public void setDescription(String description) { this.description = description; }
    public void setUserId(User userId) { this.userId = userId; }
    public void setProductId(Product productId) { this.productId = productId; }

    @Override
    public String toString() {
        return "ProductReview{" +
                "productReviewId=" + productReviewId +
                ", rating=" + rating +
                ", description='" + description + '\'' +
                ", userId=" + userId +
                ", productId=" + productId +
                '}';
    }
}
