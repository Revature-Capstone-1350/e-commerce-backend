package com.revature.dtos;

import com.revature.util.Regex;
import com.revature.util.ValidatorMessageUtil;
import com.revature.util.groups.OnCreate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotBlank( message = ValidatorMessageUtil.EMAIL_REQUIRED_ON_CREATE)
    @Email( message = ValidatorMessageUtil.EMAIL_REQUIRED_ON_CREATE)
    @NotNull( message = ValidatorMessageUtil.EMAIL_REQUIRED)
    private String email;

    @NotBlank( message = ValidatorMessageUtil.PASSWORD_REQUIRED_ON_CREATE)
    @NotNull( message = ValidatorMessageUtil.PASSWORD_REQUIRED_ON_CREATE)
    @Pattern( regexp = Regex.SYMBOL, message = ValidatorMessageUtil.PASSWORD_SYMBOL)
    @Pattern( regexp = Regex.NUMBER, message = ValidatorMessageUtil.PASSWORD_NUMBER)
    @Pattern( regexp = Regex.UPPER, message = ValidatorMessageUtil.PASSWORD_UPPER)
    @Pattern( regexp = Regex.LOWER, message = ValidatorMessageUtil.PASSWORD_LOWER)
    @Pattern( regexp = Regex.LENGTH, message = ValidatorMessageUtil.PASSWORD_LENGTH)
    @Pattern( regexp = Regex.ONLY_THESE, message = ValidatorMessageUtil.PASSWORD_ONLY_THESE)
    private String password;

    @NotNull(
            message = ValidatorMessageUtil.FNAME_REQUIRED_ON_CREATE,
            groups = OnCreate.class
    )
    @Length (
            message = ValidatorMessageUtil.FNAME_REQUIRED_ON_CREATE,
            groups = OnCreate.class
    )
    private String firstName;

    @NotNull(
            message = ValidatorMessageUtil.LNAME_REQUIRED_ON_CREATE,
            groups = OnCreate.class
    )
    @Length (
            message = ValidatorMessageUtil.LNAME_REQUIRED_ON_CREATE,
            groups = OnCreate.class
    )
    private String lastName;
}
