package com.revature.models;

import com.revature.dtos.RegisterRequest;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", updatable = false, nullable = false)
    private Integer user_id;

    @Enumerated(EnumType.STRING)
    @Column(length=25, name="role", nullable = false)
    private Role role;

    @Column(length=50, nullable = false)
    private String first_name;

    @Column(length=50)
    private String last_name;

    @Column(length=255, nullable = false)
    private String email;

    @Column(length=255, nullable = false) // TODO : Length
    private String password;

    @OneToMany(mappedBy = "user_id")
    private List<Order> user_orders;

    @OneToMany(mappedBy = "rating_id")
    private List<Rating> user_ratings; // TODO : constructor (?) and getter/setter

    public User() {
        super();
        this.role = Role.BASIC;
    }

    public User(RegisterRequest regReq) {
        this();
        this.email = regReq.getEmail();
        this.password = regReq.getPassword();
        this.first_name = regReq.getFirstName();
        this.last_name = regReq.getLastName();
    }

    public Integer getUser_id() { return user_id; }
    public Role getRole() { return role; }
    public String getFirst_name() { return first_name; }
    public String getLast_name() { return last_name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public List<Order> getUser_orders() { return user_orders; }

    public void setUser_id(Integer user_id) { this.user_id = user_id; }
    public void setRole(Role role) { this.role = role; }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUser_orders(List<Order> user_orders) { this.user_orders = user_orders; }

    public enum Role {
        ADMIN, BASIC
    }
}
