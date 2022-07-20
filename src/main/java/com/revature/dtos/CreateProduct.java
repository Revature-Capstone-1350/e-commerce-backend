package com.revature.dtos;


import com.revature.models.Product;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProduct {

    private int createId;

    private double createPrice;

    private String createDescription;

    private String createImageSmall;

    private String createImageMedium;

    private String createName;

    public Product extractResource() {
            return new Product(createId,createPrice, createDescription,createImageSmall,createImageMedium,createName);
    }

}


