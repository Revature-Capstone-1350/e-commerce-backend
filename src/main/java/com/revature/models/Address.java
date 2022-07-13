package com.revature.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id", updatable = false, nullable = false)
    private Integer address_id;

    @Column(columnDefinition = "varchar", nullable=false)
    private String street;

    @Column(columnDefinition = "varchar")
    private String street2;

    @Column(columnDefinition = "char(2)", nullable=false)
    private String state;

    @Column(length=50)
    private String city;

    @Column(length=10)
    private String postal_code;

    @Column(nullable=false)
    private Integer user_id;

    @OneToMany(mappedBy = "address_id")
    private List<Order> user_orders;

    public Address() { super(); }

    public Integer getAddress_id() { return address_id; }
    public String getStreet() { return street; }
    public String getStreet2() { return street2; }
    public String getState() { return state; }
    public String getCity() { return city; }
    public String getPostal_code() { return postal_code; }
    public Integer getUser_id() { return user_id; }
    public List<Order> getUser_orders() { return user_orders; }

    public void setAddress_id(Integer address_id) { this.address_id = address_id; }
    public void setStreet(String street) { this.street = street; }
    public void setStreet2(String street2) { this.street2 = street2; }
    public void setState(String state) { this.state = state; }
    public void setCity(String city) { this.city = city; }
    public void setPostal_code(String postal_code) { this.postal_code = postal_code; }
    public void setUser_id(Integer user_id) { this.user_id = user_id; }
    public void setUser_orders(List<Order> user_orders) { this.user_orders = user_orders; }

}
