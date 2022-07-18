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
    private User user;

    @ManyToOne // one user, many ratings; each rating has one associated user
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // constructors
    public ProductReview() { super(); } // required no-args constructor

    public ProductReview(Integer rating, String description, User user, Product product) {
        this();
        this.rating = rating;
        this.description = description;
        this.user = user;
        this.product = product;
    }

    // getters
    public Integer getProductReviewId() { return productReviewId; }
    public Integer getRating() { return rating; }
    public String getDescription() { return description; }
    public User getUser() { return user; }
    public Product getProduct() { return product; }

    // setters
//    public void setProductReviewId(Integer productReviewId) { this.productReviewId = productReviewId; }
    public void setRating(Integer rating) { this.rating = rating; }
    public void setDescription(String description) { this.description = description; }
    public void setUser(User user) { this.user = user; }
    public void setProduct(Product product) { this.product = product; }

    @Override
    public String toString() {
        return "ProductReview{" +
                "productReviewId=" + productReviewId +
                ", rating=" + rating +
                ", description='" + description + '\'' +
                ", userId=" + user +
                ", productId=" + product +
                '}';
    }
}
