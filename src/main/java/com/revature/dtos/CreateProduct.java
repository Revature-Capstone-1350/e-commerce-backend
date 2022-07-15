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

    private int createQuantity;

    private double createPrice;

    private String createDescription;

    private String createImage;

    private String createName;

    public Product extractResource() {
            return new Product(createId,createQuantity,createPrice,createDescription,createImage,createName);
    }

}


