package com.revature.dtos;

import com.revature.models.Category;
import com.revature.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProduct {

    private String name;
    private String description;
    private double price;
    private String imageUrlS;
    private String imageUrlM;
    private int category;

}