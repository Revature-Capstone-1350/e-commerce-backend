package com.revature.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity // necessary annotation to mark this class as an entity bean
@Table(name = "`product`") // specifies database table name
public class Product {

    @Id // Identifies this column as a primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-incrementing
    @Column(name = "product_id", // defines column name in the database
            insertable = false, // this column is not included in generated INSERT statements
            updatable = false,  // this column is not included in generated UPDATE statements
            nullable = false
            )
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

    @OneToMany(mappedBy = "product")
    private List<ProductReview> productReviews;

    // constructors
    public Product() { super(); } // required no-args constructor

    public Product(Category category, String name, String description, double price, String image_url_s, String image_url_m) {
        this();
        this.category = category;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image_url_s = image_url_s;
        this.image_url_m = image_url_m;
    }

    // getters
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

    // setters
//    public void setProductId(Integer productId) { this.productId = productId; }
    public void setCategory(Category category) { this.category = category; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setPrice(double price) { this.price = price; }
    public void setImage_url_s(String image_url_s) { this.image_url_s = image_url_s; }
    public void setImage_url_m(String image_url_m) { this.image_url_m = image_url_m; }
    public void setImage_url_l(String image_url_l) { this.image_url_l = image_url_l; }
    public void setRatings(List<ProductReview> productReviews) { this.productReviews = productReviews; }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", image_url_s='" + image_url_s + '\'' +
                ", image_url_m='" + image_url_m + '\'' +
                ", image_url_l='" + image_url_l + '\'' +
                ", category=" + category +
                ", productReviews.size()=" + productReviews.size() +
                '}';
    }
}
