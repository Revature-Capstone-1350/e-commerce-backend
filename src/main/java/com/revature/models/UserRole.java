package com.revature.models;

import javax.persistence.*;

@Entity
@Table(name = "user_role")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", updatable = false, nullable = false)
    private Integer role_id;

    @Column(length=50)
    private String name;

    public Integer getRole_id() { return role_id; }
    public String getName() { return name; }

    public void setRole_id(Integer role_id) { this.role_id = role_id; }
    public void setName(String name) { this.name = name; }

    public UserRole() { super(); }

    public UserRole(Integer role_id, String name) {
        this();
        this.role_id = role_id;
        this.name = name;
    }
}
