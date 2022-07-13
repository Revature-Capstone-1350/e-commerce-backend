package com.revature.models;

import javax.persistence.*;

@Entity
@Table(name = "product")
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

    @Column(nullable = false)
    private Integer category_id;

    @Column(nullable = false)
    private Integer vendor_id;

    @Column(nullable = false)
    private double price;

    @Column(columnDefinition = "varchar")
    private String image_url;

    public Product() { super(); }

    public Integer getProduct_id() { return product_id; }
    public Category getCategory() { return category; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public Integer getCategory_id() { return category_id; }
    public Integer getVendor_id() { return vendor_id; }
    public double getPrice() { return price; }
    public String getImage_url() { return image_url; }

    public void setProduct_id(Integer product_id) { this.product_id = product_id; }
    public void setCategory(Category category) { this.category = category; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setCategory_id(Integer category_id) { this.category_id = category_id; }
    public void setVendor_id(Integer vendor_id) { this.vendor_id = vendor_id; }
    public void setPrice(double price) { this.price = price; }
    public void setImage_url(String image_url) { this.image_url = image_url; }

    public enum Category {
        CATEGORY1, CATEGORY2, CATEGORY3, CATEGORY4,
        CATEGORY5, CATEGORY6
    }
}
