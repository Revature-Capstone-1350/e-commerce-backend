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
    private String name;
    private String location;
    private String description;
    private String date;
    private double price;
    private String imgUrlSmall;
    private String imgUrlMed;
    private String category;
    private int numberOfRatings;
    private int sumOfRatings;

    public ProductInfo(Product product) {
        this.productId = product.getProductId();
        this.name = product.getName();
        this.location = product.getLocation();
        this.description = product.getDescription();
        this.date = product.getDate();
        this.price = product.getPrice();
        this.imgUrlSmall = product.getImageUrlS();
        this.imgUrlMed = product.getImageUrlM();
        this.category = product.getCategory().toString();
        this.numberOfRatings = product.getRatings().size();
    }

    public void setSumOfRating(int sumOfRatings) {
        this.sumOfRatings = sumOfRatings;
    }
}
