package com.revature.models;

import com.revature.dtos.CreateProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantity;
    private double price;
    private String description;
    private String image;
    private String name;


    public Product(CreateProduct createProduct) {
        this.id = createProduct.getCreateId();
        this.quantity = createProduct.getCreateQuantity();
        this.price = createProduct.getCreatePrice();
        this.description = createProduct.getCreateDescription();
        this.image = createProduct.getCreateImage();
        this.name = createProduct.getCreateName();
    }


}

