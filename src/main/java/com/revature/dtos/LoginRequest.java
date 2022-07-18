package com.revature.dtos;

import com.revature.util.Regex;
import com.revature.util.ValidatorMessageUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NotNull( message = ValidatorMessageUtil.EMAIL_REQUIRED)
    @Email( message = ValidatorMessageUtil.EMAIL_REQUIREMENTS)
    private String email;

    @NotNull( message = ValidatorMessageUtil.PASSWORD_REQUIRED)
    @Pattern( regexp = Regex.PASSWORD, message = ValidatorMessageUtil.PASSWORD_REQUIREMENTS)
    private String password;
}
