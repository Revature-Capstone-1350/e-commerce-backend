package com.revature.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "product_review")
public class ProductReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_review_id", nullable = false)
    private Integer product_review_id;

    @JoinColumn(name = "user_id", nullable = false)
    private Integer user_id;

    @JoinColumn(name = "product_id", nullable = false)
    private Integer productId;

    @Column
    private String description;

    @Column(nullable = false)
    private int rating;

    public ProductReview() {
    }

    public ProductReview(Integer product_review_id, Integer user_id, Integer product_id, String description, int rating) {
        this.product_review_id = product_review_id;
        this.user_id = user_id;
        this.productId = product_id;
        this.description = description;
        this.rating = rating;
    }

    public Integer getProduct_review_id() {
        return product_review_id;
    }

    public void setProduct_review_id(Integer product_review_id) {
        this.product_review_id = product_review_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getProduct_id() {
        return productId;
    }

    public void setProduct_id(Integer product_id) {
        this.productId = product_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductReview that = (ProductReview) o;
        return rating == that.rating && Objects.equals(product_review_id, that.product_review_id) && Objects.equals(user_id, that.user_id) && Objects.equals(productId, that.productId) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product_review_id, user_id, productId, description, rating);
    }

    @Override
    public String toString() {
        return "ProductReview{" +
                "product_review_id=" + product_review_id +
                ", user_id=" + user_id +
                ", product_id=" + productId +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                '}';
    }
}
