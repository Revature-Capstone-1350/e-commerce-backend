package com.revature.models;

import javax.persistence.*;
import java.util.List;

@Entity // necessary annotation to mark this class as an entity bean
@Table(name = "`order`") // specifies database table name
public class Order {

    @Id // Identifies this column as a primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-incrementing
    @Column(name = "order_id",  // defines column name in the database
            insertable = false, // this column is not included in generated INSERT statements
            updatable = false,  // this column is not included in generated UPDATE statements
            nullable = false
            )
    private Integer orderId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address addressId;

    @ManyToOne
    @JoinColumn(name = "order_status_id", nullable = false)
    private OrderStatus status;

    @ManyToMany
    @JoinTable(
            name="`order_item`",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> items;

    // constructors
    public Order() { super(); } // required no-args constructor

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
