package com.revature.models;

import javax.persistence.*;

@Entity
@Table(name = "order_statuses")
public class OrderStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_status_id", updatable = false, nullable = false)
    private Integer orderStatusId;

    @Column(length=50)
    private String name;

    // constructors
    public OrderStatus() { super(); }

    public OrderStatus(Integer orderStatusId, String name) {
        this.orderStatusId = orderStatusId;
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
