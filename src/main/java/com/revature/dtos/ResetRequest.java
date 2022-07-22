package com.revature.dtos;

import com.revature.util.Regex;
import com.revature.util.ValidatorMessageUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResetRequest {

    @NotNull // cannot be missing
    private String oldPassword;

    @Pattern( regexp = Regex.SYMBOL, message = ValidatorMessageUtil.PASSWORD_SYMBOL)
    @Pattern( regexp = Regex.NUMBER, message = ValidatorMessageUtil.PASSWORD_NUMBER)
    @Pattern( regexp = Regex.UPPER, message = ValidatorMessageUtil.PASSWORD_UPPER)
    @Pattern( regexp = Regex.LOWER, message = ValidatorMessageUtil.PASSWORD_LOWER)
    @Pattern( regexp = Regex.LENGTH, message = ValidatorMessageUtil.PASSWORD_LENGTH)
    @Pattern( regexp = Regex.ONLY_THESE, message = ValidatorMessageUtil.PASSWORD_ONLY_THESE)
    private String newPassword;

    private String newEmail;

    private String newFirstname;

    private String newLastname;
}
