package com.revature.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", updatable = false, nullable = false)
    private Integer order_id;

    @Enumerated(EnumType.STRING)
    @Column(length=25, name="status")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user_id;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address_id;

    @ManyToMany
    @JoinTable(
            name="order_item",
            joinColumns = @JoinColumn(name="order_id"),
            inverseJoinColumns = @JoinColumn(name="product_id")
    )
    private List<Product> items;

    public Order() { super(); }

    public Integer getOrder_id() { return order_id; }
    public Status getStatus() { return status; }
    public User getUser_id() { return user_id; }
    public Address getAddress_id() { return address_id; }
    public List<Product> getItems() { return items; }

    public void setOrder_id(Integer order_id) { this.order_id = order_id; }
    public void setStatus(Status status) { this.status = status; }
    public void setUser_id(User user_id) { this.user_id = user_id; }
    public void setAddress_id(Address address_id) { this.address_id = address_id; }
    public void setItems(List<Product> items) { this.items = items; }

    public enum Status {
        INPROGRESS, COMPLETED, PAYMENTDECLINED
    }
}
