package com.revature.dtos;

import com.revature.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInfo {
    private Integer productId;
    private String category;
    private String name;
    private String description;
    private double price;
    private String imgUrlSmall;
    private String imgUrlMed;
    private int numberOfRatings;
    private double averageRating;

    public ProductInfo(Product product) {
        this.productId = product.getProductId();
        this.category = product.getCategory().toString();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.imgUrlSmall = product.getImageUrlS();
        this.imgUrlMed = product.getImageUrlM();
        this.numberOfRatings = product.getRatings().size();
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }
}
