package com.revature.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResetRequest {

    @NotNull // cannot be missing
    private String oldPassword;

    @NotNull // cannot be missing
    private String newPassword;

    @NotNull // cannot be missing
    private String newEmail;

    @NotNull // cannot be missing
    private String newFirstname;

    @NotNull // cannot be missing
    private String newLastname;
}
