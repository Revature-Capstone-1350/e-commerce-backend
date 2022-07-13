package com.revature.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", updatable = false, nullable = false)
    private Integer product_id;

    @Enumerated(EnumType.STRING)
    @Column(length=25, name="category")
    private Category category;

    @Column(length=50, nullable = false)
    private String name;

    @Column(columnDefinition = "text")
    private String description;

    @Column(columnDefinition = "int default 1")
    private Integer vendor_id; // There's only one vendor

    @Column(nullable = false)
    private double price;

    @Column(columnDefinition = "varchar")
    private String image_url;

    @OneToMany(mappedBy = "rating_id")
    private List<Rating> ratings;

    public Product() { super(); }

    public Product(Category category, String name, String description, double price, String image_url) {
        this();
        this.category = category;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image_url = image_url;
    }

    public Integer getProduct_id() { return product_id; }
    public Category getCategory() { return category; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public Integer getVendor_id() { return vendor_id; }
    public double getPrice() { return price; }
    public String getImage_url() { return image_url; }
    public List<Rating> getRatings() {
        if (ratings == null) return new ArrayList<>();
        return ratings;
    }

    public void setProduct_id(Integer product_id) { this.product_id = product_id; }
    public void setCategory(Category category) { this.category = category; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setVendor_id(Integer vendor_id) { this.vendor_id = vendor_id; }
    public void setPrice(double price) { this.price = price; }
    public void setImage_url(String image_url) { this.image_url = image_url; }
    public void setRatings(List<Rating> ratings) { this.ratings = ratings; }

    public enum Category {
        CATEGORY1, CATEGORY2, CATEGORY3, CATEGORY4,
        CATEGORY5, CATEGORY6
    }
}
