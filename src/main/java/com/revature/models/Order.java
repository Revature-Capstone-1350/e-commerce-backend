package com.revature.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "`order`")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", updatable = false, nullable = false)
    private Integer orderId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address addressId;

    @ManyToOne
    @JoinColumn(name="order_status_id", nullable = false)
    private OrderStatus status;

    @ManyToMany
    @JoinTable(
            name="order_item",
            joinColumns = @JoinColumn(name="order_id"),
            inverseJoinColumns = @JoinColumn(name="product_id")
    )
    private List<Product> items;

    // constructors
    public Order() { super(); }

    // getters
    public Integer getOrderId() { return orderId; }
    public OrderStatus getStatus() { return status; }
    public User getUserId() { return userId; }
    public Address getAddressId() { return addressId; }
    public List<Product> getItems() { return items; }

    // setters
//    public void setOrderId(Integer orderId) { this.orderId = orderId; }
    public void setStatus(OrderStatus status) { this.status = status; }
    public void setUserId(User userId) { this.userId = userId; }
    public void setAddressId(Address addressId) { this.addressId = addressId; }
    public void setItems(List<Product> items) { this.items = items; }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", addressId=" + addressId +
                ", status=" + status +
                ", items.size()=" + items.size() +
                '}';
    }
}
