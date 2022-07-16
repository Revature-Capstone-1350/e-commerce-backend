package com.revature.dtos;

import com.revature.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInfo {
    private Integer product_id;
    private String category;
    private String name;
    private String description;
    private double price;
    private String imageUrlS;
    private String imageUrlM;
    //private int number_of_ratings;

    public ProductInfo(Product product) {
        this.product_id = product.getProductId();
        this.category = product.getCategory().toString();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.imageUrlS = product.getImageUrlS();
        this.imageUrlM = product.getImageUrlM();
        //this.number_of_ratings = product.getRatings().size();
    }
}
