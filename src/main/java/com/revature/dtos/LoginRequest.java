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

    @NotBlank( message = ValidatorMessageUtil.EMAIL_REQUIRED)
    @NotNull( message = ValidatorMessageUtil.EMAIL_REQUIRED)
    @Email( message = ValidatorMessageUtil.EMAIL_REQUIREMENTS)
    private String email;

    @NotNull( message = ValidatorMessageUtil.PASSWORD_REQUIRED)
    @Pattern( regexp = Regex.SYMBOL, message = ValidatorMessageUtil.PASSWORD_SYMBOL)
    @Pattern( regexp = Regex.NUMBER, message = ValidatorMessageUtil.PASSWORD_NUMBER)
    @Pattern( regexp = Regex.UPPER, message = ValidatorMessageUtil.PASSWORD_UPPER)
    @Pattern( regexp = Regex.LOWER, message = ValidatorMessageUtil.PASSWORD_LOWER)
    @Pattern( regexp = Regex.LENGTH, message = ValidatorMessageUtil.PASSWORD_LENGTH)
    @Pattern( regexp = Regex.ONLY_THESE, message = ValidatorMessageUtil.PASSWORD_ONLY_THESE)
    private String password;
}
