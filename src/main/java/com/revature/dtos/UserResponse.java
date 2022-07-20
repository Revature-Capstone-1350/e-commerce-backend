package com.revature.dtos;

import com.revature.models.User;
import com.revature.models.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponse {

    private Integer userId;

    private String email;

    private UserRole role;

    private String firstName;

    private String lastName;

    public UserResponse(User user){
        this.userId = user.getUserId();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
    }

}
