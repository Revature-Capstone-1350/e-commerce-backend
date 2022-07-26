package com.revature.models;

import javax.persistence.*;

@Entity // necessary annotation to mark this class as an entity bean
@Table(name = "`category`") // specifies database table name
public class Category {

    @Id // Identifies this column as a primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-incrementing
    @Column(name = "category_id", // defines column name in the database
            insertable = false,   // this column is not included in generated INSERT statements
            updatable = false,    // this column is not included in generated UPDATE statements
            nullable = false
            )
    private Integer categoryId;

    @Column(length = 50)
    private String name;

    // constructors
    public Category() { super(); } // required no-args constructor

    public Category(String name) { this.name = name; }

    public Category(Integer categoryId) { this.categoryId = categoryId; }

    // getters
    public Integer getCategoryId() { return categoryId; }
    public String getName() { return name; }

    // setters
    // We have no need to set the ID
    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", name='" + name + '\'' +
                '}';
    }
}
