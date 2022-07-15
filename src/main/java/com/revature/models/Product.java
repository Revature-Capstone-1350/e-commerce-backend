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

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column(nullable = false)
    private double price;

    @Column
    private String image_url_s;

    @Column
    private String image_url_m;

    @Column
    private String image_url_l;

    @OneToMany(mappedBy = "rating_id")
    private List<Rating> ratings;

    public Product() { super(); }

    public Product(Category category, String name, String description, double price, String image_url) {
        this();
        this.category = category;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image_url_s = image_url;
    }

    public Product(Category category, String name, String description, double price, String image_url_s, String image_url_m) {
        this();
        this.category = category;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image_url_s = image_url_s;
        this.image_url_m = image_url_m;
    }

    public Integer getProduct_id() { return product_id; }
    public Category getCategory() { return category; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public String getImage_url_s() { return image_url_s; }
    public String getImage_url_m() { return image_url_m; }
    public String getImage_url_l() { return image_url_l; }
    public List<Rating> getRatings() {
        if (ratings == null) return new ArrayList<>();
        return ratings;
    }

    public void setProduct_id(Integer product_id) { this.product_id = product_id; }
    public void setCategory(Category category) { this.category = category; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setPrice(double price) { this.price = price; }
    public void setImage_url_s(String image_url_s) { this.image_url_s = image_url_s; }
    public void setImage_url_m(String image_url_m) { this.image_url_m = image_url_m; }
    public void setImage_url_l(String image_url_l) { this.image_url_l = image_url_l; }
    public void setRatings(List<Rating> ratings) { this.ratings = ratings; }

    public enum Category {
        DAY, NIGHT, DAWN, DUSK, SPACE, CLOUD, MOON, SUN
    }
}
