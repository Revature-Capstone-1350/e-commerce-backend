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

    public Category() { super(); }

    public Category(String name) { this.name = name; }
}
