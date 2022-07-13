package com.revature.dtos;

import com.revature.models.User;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Login and Register responds with this
 */

@Data
@NoArgsConstructor
public class AuthResponse {
    private String email;
    private String firstName;
    private String lastName;

    public AuthResponse(User user) {
        this.email = user.getEmail();
        this.firstName = user.getFirst_name();
        this.lastName = user.getLast_name();
    }
}
