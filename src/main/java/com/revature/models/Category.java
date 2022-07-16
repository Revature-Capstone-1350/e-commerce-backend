package com.revature.models;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", updatable = false, nullable = false)
    private Integer categoryId;

    @Column(length=50)
    private String name;

    // constructors
    public Category() { super(); }

    public Category(String name) { this.name = name; }

    // getters
    public Integer getCategoryId() { return categoryId; }
    public String getName() { return name; }

    // setters
//    public void setCategoryId(Integer categoryId) { this.categoryId = categoryId; }
    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", name='" + name + '\'' +
                '}';
    }
}
