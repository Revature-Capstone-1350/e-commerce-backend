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
    private double price;
    private String description;
    private String imageSmall;
    private String imageMedium;
    private String name;


    public Product(CreateProduct createProduct) {
        this.id = createProduct.getCreateId();
        this.price = createProduct.getCreatePrice();
        this.description = createProduct.getCreateDescription();
        this.imageSmall = createProduct.getCreateImageSmall();
        this.imageMedium = createProduct.getCreateImageMedium();
        this.name = createProduct.getCreateName();
    }

}

