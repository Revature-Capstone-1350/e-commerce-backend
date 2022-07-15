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
    private String image_url_s;
    private String image_url_m;
    //private int number_of_ratings;

    public ProductInfo(Product product) {
        this.product_id = getProduct_id();
        this.category = product.getCategory().toString();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.image_url_s = product.getImage_url_s();
        this.image_url_m = product.getImage_url_m();
        //this.number_of_ratings = product.getRatings().size();
    }
}
