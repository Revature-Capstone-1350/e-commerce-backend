package com.revature.dtos;

import com.revature.models.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Principal {

    private int authUserId;
    private String authUserEmail;
    private Boolean isAdmin = false;

    public Principal(User user) {
        this.authUserId = user.getUserId();
        this.authUserEmail = user.getEmail();
    }

    public Principal(int authUserId) {
        this.authUserId = authUserId;
    }

    public Principal(int authUserId, String authUserEmail) {
        this.authUserId = authUserId;
        this.authUserEmail = authUserEmail;
    }
    private void makeAdmin() {
        this.isAdmin = true;
    }
}
