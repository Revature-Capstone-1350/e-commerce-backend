package com.revature.models;

import javax.persistence.*;

@Entity // necessary annotation to mark this class as an entity bean
@Table(name = "`user_role`") // specifies database table name
public class UserRole {

    @Id // Identifies this column as a primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-incrementing
    @Column(name = "role_id", // defines column name in the database
            insertable = false, // this column is not included in generated INSERT statements
            updatable = false,  // this column is not included in generated UPDATE statements
            nullable = false
            )
    private Integer roleId;

    @Column(length = 50)
    private String name;

    // constructors
    public UserRole() { super(); } // required no-args constructor

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
