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

    @Column(length=50, name="first_name", nullable = false)
    private String firstName;

    @Column(length=50, name="last_name")
    private String lastName;

    @Column(length=255, nullable = false)
    private String email;

    @Column(length=64, nullable = false) // TODO : Length
    private String password;

    @ManyToOne // one role, many users; each user has 1 role
    @JoinColumn(name = "role_id", nullable = false)
    private UserRole role;

    @OneToMany(mappedBy = "userId")
    private List<Order> userOrders;

    @OneToMany(mappedBy = "productReviewId")
    private List<ProductReview> userProductReviews; // TODO : constructor (?) and getter/setter

    // constructors
    public User() {
        super();
    }

    public User(RegisterRequest regReq) {
        this();
        this.email = regReq.getEmail();
        this.password = regReq.getPassword();
        this.firstName = regReq.getFirstName();
        this.lastName = regReq.getLastName();
    }

    // getters
    public Integer getUser_id() { return user_id; }
    public UserRole getRole() { return role; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public List<Order> getUserOrders() { return userOrders; }

    // setters
//    public void setUser_id(Integer user_id) { this.user_id = user_id; }
    public void setRole(UserRole role) { this.role = role; }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUserOrders(List<Order> userOrders) { this.userOrders = userOrders; }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", userOrders.size()=" + userOrders.size() +
                ", userProductReviews.size()=" + userProductReviews.size() +
                '}';
    }
}
