package com.revature.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id", updatable = false, nullable = false)
    private Integer addressId;

    @Column(nullable=false)
    private String street;

    @Column
    private String street2;

    @Column(length=2, nullable=false)
    private String state;

    @Column(length=50)
    private String city;

    @Column(length=10, name="postal_code")
    private String postalCode;

    @OneToMany(mappedBy = "address_id")
    private List<Order> orderList;

    public Address() { super(); }

    public Integer getAddressId() { return addressId; }
    public String getStreet() { return street; }
    public String getStreet2() { return street2; }
    public String getState() { return state; }
    public String getCity() { return city; }
    public String getPostalCode() { return postalCode; }
    public List<Order> getOrderList() { return orderList; }

    public void setAddressId(Integer addressId) { this.addressId = addressId; }
    public void setStreet(String street) { this.street = street; }
    public void setStreet2(String street2) { this.street2 = street2; }
    public void setState(String state) { this.state = state; }
    public void setCity(String city) { this.city = city; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }
    public void setOrderList(List<Order> orderList) { this.orderList = orderList; }

}
