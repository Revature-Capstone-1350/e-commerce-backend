package com.revature.dtos;

public class CreateProduct {
    private int createId;
    private int createQuantity;
    private double createPrice;
    private String createDescription;
    private String createImage;
    private String createName;

    public CreateProduct(int createId, int createQuantity, double createPrice, String createDescription, String createImage, String createName) {
        this.createId = createId;
        this.createQuantity = createQuantity;
        this.createPrice = createPrice;
        this.createDescription = createDescription;
        this.createImage = createImage;
        this.createName = createName;
    }




}
