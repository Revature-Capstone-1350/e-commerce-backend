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
    private Integer productId;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private String image_url_s;

    @Column(nullable = false)
    private String image_url_m;

    @Column // might get all large images later, but nullable for now
    private String image_url_l;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "rating_id")
    private List<ProductReview> productReviews;

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

    public Integer getProductId() { return productId; }
    public Category getCategory() { return category; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public String getImage_url_s() { return image_url_s; }
    public String getImage_url_m() { return image_url_m; }
    public String getImage_url_l() { return image_url_l; }
    public List<ProductReview> getRatings() {
        if (productReviews == null) return new ArrayList<>();
        return productReviews;
    }

    public void setProductId(Integer productId) { this.productId = productId; }
    public void setCategory(Category category) { this.category = category; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setPrice(double price) { this.price = price; }
    public void setImage_url_s(String image_url_s) { this.image_url_s = image_url_s; }
    public void setImage_url_m(String image_url_m) { this.image_url_m = image_url_m; }
    public void setImage_url_l(String image_url_l) { this.image_url_l = image_url_l; }
    public void setRatings(List<ProductReview> productReviews) { this.productReviews = productReviews; }

}
