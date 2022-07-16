package com.revature.models;

import javax.persistence.*;

@Entity
@Table(name = "order_status")
public class OrderStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_status_id", updatable = false, nullable = false)
    private Integer orderStatusId;

    @Column(length=50)
    private String name;

    public Integer getOrderStatusId() { return orderStatusId; }
    public String getName() { return name; }

    public void setOrderStatusId(Integer orderStatusId) { this.orderStatusId = orderStatusId; }
    public void setName(String name) { this.name = name; }

    public OrderStatus() { super(); }

    public OrderStatus(Integer orderStatusId, String name) {
        this.orderStatusId = orderStatusId;
        this.name = name;
    }
}
