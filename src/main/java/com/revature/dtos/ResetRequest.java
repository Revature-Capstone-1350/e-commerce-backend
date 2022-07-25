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

    @Pattern( regexp = Regex.SYMBOL, message = ValidatorMessageUtil.PASSWORD_SYMBOL)
    @Pattern( regexp = Regex.NUMBER, message = ValidatorMessageUtil.PASSWORD_NUMBER)
    @Pattern( regexp = Regex.UPPER, message = ValidatorMessageUtil.PASSWORD_UPPER)
    @Pattern( regexp = Regex.LOWER, message = ValidatorMessageUtil.PASSWORD_LOWER)
    @Pattern( regexp = Regex.LENGTH, message = ValidatorMessageUtil.PASSWORD_LENGTH)
    @Pattern( regexp = Regex.ONLY_THESE, message = ValidatorMessageUtil.PASSWORD_ONLY_THESE)
    @NotNull // cannot be missing
    private String oldPassword;

    @Pattern( regexp = Regex.SYMBOL_OR_BLANK, message = "New "+ValidatorMessageUtil.PASSWORD_SYMBOL)
    @Pattern( regexp = Regex.NUMBER_OR_BLANK, message = "New "+ValidatorMessageUtil.PASSWORD_NUMBER)
    @Pattern( regexp = Regex.UPPER_OR_BLANK, message = "New "+ValidatorMessageUtil.PASSWORD_UPPER)
    @Pattern( regexp = Regex.LOWER_OR_BLANK, message = "New "+ValidatorMessageUtil.PASSWORD_LOWER)
    @Pattern( regexp = Regex.LENGTH_OR_BLANK, message = "New "+ValidatorMessageUtil.PASSWORD_LENGTH)
    @Pattern( regexp = Regex.ONLY_THESE_OR_BLANK, message = "New "+ValidatorMessageUtil.PASSWORD_ONLY_THESE)
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
