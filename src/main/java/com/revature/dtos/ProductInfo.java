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
    private Integer vendor_id;
    private double price;
    private String image_url;
    private int number_of_ratings;

    public ProductInfo(Product product) {
        this.product_id = getProduct_id();
        this.category = product.getCategory().toString();
        this.name = product.getName();
        this.description = product.getDescription();
        this.vendor_id = product.getVendor_id();
        this.price = product.getPrice();
        this.image_url = product.getImage_url();
        this.number_of_ratings = product.getRatings().size();
    }
}
