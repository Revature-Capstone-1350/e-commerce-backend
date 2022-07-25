package com.revature.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest {

    private String name;
    private String description;
    private double price;
    private String imageUrlS;
    private String imageUrlM;
    private int category;

}
