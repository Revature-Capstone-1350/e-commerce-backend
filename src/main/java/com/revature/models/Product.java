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

    @Column(length=50, nullable = false)
    private String name; // person who posted or provided image

    @Column(length=50)
    private String description; // What's showcased in the photo

    @Column(nullable = false)
    private double price;

    @Column(name = "image_url_s", nullable = false)
    private String imageUrlS;

    @Column(name = "image_url_m", nullable = false)
    private String imageUrlM;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<ProductReview> productReviews;

    // constructors
    public Product() { super(); } // required no-args constructor

    public Product(String name, String location, String description, String date, double price, String imageUrlS, String imageUrlM, String imageUrlL, Category category) {
        this();
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrlS = imageUrlS;
        this.imageUrlM = imageUrlM;
        this.category = category;
    }

    public Product(Category category, String name, String description, double price, String imageUrlS, String imageUrlM) {
        this();
        this.category = category;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrlS = imageUrlS;
        this.imageUrlM = imageUrlM;
    }

    // getters
    public Integer getProductId() { return productId; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public String getImageUrlS() { return imageUrlS; }
    public String getImageUrlM() { return imageUrlM; }
    public Category getCategory() { return category; }
    public List<ProductReview> getRatings() {
        if (productReviews == null) return new ArrayList<>();
        return productReviews;
    }

    // setters
//    public void setProductId(Integer productId) { this.productId = productId; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setPrice(double price) { this.price = price; }
    public void setImageUrlS(String imageUrlS) { this.imageUrlS = imageUrlS; }
    public void setImageUrlM(String imageUrlM) { this.imageUrlM = imageUrlM; }
    public void setCategory(Category category) { this.category = category; }
    public void setRatings(List<ProductReview> productReviews) { this.productReviews = productReviews; }



    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", image_url_s='" + imageUrlS + '\'' +
                ", image_url_m='" + imageUrlM + '\'' +
                ", category=" + category +
                ", productReviews.size()=" + ((productReviews == null)?0:productReviews.size()) +
                '}';
    }
}
