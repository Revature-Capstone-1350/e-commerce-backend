package com.revature.dtos;

public class ProductRequest {
    private int id;
    private String name;
    private String description;
    private double price;
    private String imageUrlS;
    private String imageUrlM;
    private int category;

    ProductRequest(int id, String name, String description, double price, String urls, String urlm, int category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrlS = urls;
        this.imageUrlM = urlm;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageUrlS() {
        return imageUrlS;
    }

    public void setImageUrlS(String imageUrlS) {
        this.imageUrlS = imageUrlS;
    }

    public String getImageUrlM() {
        return imageUrlM;
    }

    public void setImageUrlM(String imageUrlM) {
        this.imageUrlM = imageUrlM;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}
