package com.revature.dtos;

import com.revature.models.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Principal {

    private int authUserId;
    private String authUserEmail;
    private String authUsername;

    public Principal(User user) {
        this.authUserId = user.getUser_id();
        this.authUserEmail = user.getEmail();
    }

    public Principal(int authUserId, String authUserEmail) {
        this.authUserId = authUserId;
        this.authUserEmail = authUserEmail;
    }

//    public Principal(LoginResponse loginResponse) {
//        this.authUserId = Long.parseLong(loginResponse.getId());
//        this.authUsername = loginResponse.getUsername();
//        this.authUserRole = loginResponse.getRole();
//    }
}
