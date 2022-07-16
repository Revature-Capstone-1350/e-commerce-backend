package com.revature.models;

import javax.persistence.*;

@Entity // necessary annotation to mark this class as an entity bean
@Table(name = "`order_status`") // specifies database table name
public class OrderStatus {

    @Id // Identifies this column as a primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-incrementing
    @Column(name = "order_status_id", // defines column name in the database
            insertable = false, // this column is not included in generated INSERT statements
            updatable = false,  // this column is not included in generated UPDATE statements
            nullable = false
            )
    private Integer orderStatusId;

    @Column(length = 50)
    private String name;

    // constructors
    public OrderStatus() { super(); } // required no-args constructor

    public OrderStatus(String name) {
        this.name = name;
    }

    // getters
    public Integer getOrderStatusId() { return orderStatusId; }
    public String getName() { return name; }

    // setters
//    public void setOrderStatusId(Integer orderStatusId) { this.orderStatusId = orderStatusId; }
    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return "OrderStatus{" +
                "orderStatusId=" + orderStatusId +
                ", name='" + name + '\'' +
                '}';
    }
}
