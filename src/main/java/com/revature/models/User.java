package com.revature.models;

import com.revature.dtos.RegisterRequest;

import javax.persistence.*;
import java.util.List;

@Entity // necessary annotation to mark this class as an entity bean
@Table(name = "`user`") // specifies database table name
public class User {

    @Id // Identifies this column as a primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-incrementing
    @Column(name = "user_id", // defines column name in the database
            insertable = false, // this column is not included in generated INSERT statements
            updatable = false,  // this column is not included in generated UPDATE statements
            nullable = false
            )
    private Integer userId;

    @Column(length = 50, name = "first_name", nullable = false)
    private String firstName;

    @Column(length = 50, name = "last_name")
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(length = 64, nullable = false) // TODO : Length
    private String password;

    @ManyToOne // one role, many users; each user has 1 role
    @JoinColumn(name = "role_id", nullable = false)
    private UserRole role;

    @OneToMany(mappedBy = "user")
    private List<Order> userOrders;

    @OneToMany(mappedBy = "productReviewId")
    private List<ProductReview> userProductReviews; // TODO : constructor (?) and getter/setter

    // constructors
    public User() {
        super();
    } // required no-args constructor

    public User(RegisterRequest regReq) {
        this();
        this.email = regReq.getEmail();
        this.password = regReq.getPassword();
        this.firstName = regReq.getFirstName();
        this.lastName = regReq.getLastName();
    }

    public User(String firstName, String lastName, String email, String password, UserRole role, List<Order> userOrders, List<ProductReview> userProductReviews) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.userOrders = userOrders;
        this.userProductReviews = userProductReviews;
    }

    // getters
    public Integer getUserId() { return userId; }
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
                "user_id=" + userId +
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
