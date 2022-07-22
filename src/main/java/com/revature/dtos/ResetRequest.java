package com.revature.dtos;

import com.revature.util.Regex;
import com.revature.util.ValidatorMessageUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResetRequest {

    @NotNull // cannot be missing
    private String oldPassword;

    @NotNull // cannot be missing
    private String newPassword;

    @Email( message = ValidatorMessageUtil.EMAIL_REQUIREMENTS)
    @NotNull // cannot be missing
    private String newEmail;

    @NotNull // cannot be missing
    private String newFirstname;

    @NotNull // cannot be missing
    private String newLastname;
}
