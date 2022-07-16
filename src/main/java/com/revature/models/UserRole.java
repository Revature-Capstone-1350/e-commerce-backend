package com.revature.models;

import javax.persistence.*;

@Entity
@Table(name = "`user_role`")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", updatable = false, nullable = false)
    private Integer roleId;

    @Column(length=50)
    private String name;

    // constructors
    public UserRole() { super(); }

    public UserRole(Integer roleId, String name) {
        this();
        this.roleId = roleId;
        this.name = name;
    }

    // getters
    public Integer getRoleId() { return roleId; }
    public String getName() { return name; }

    // setters
//    public void setRoleId(Integer roleId) { this.roleId = roleId; }
    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return "UserRole{" +
                "roleId=" + roleId +
                ", name='" + name + '\'' +
                '}';
    }
}
