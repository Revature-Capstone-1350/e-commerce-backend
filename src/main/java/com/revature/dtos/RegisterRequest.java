package com.revature.dtos;

import com.revature.util.Regex;
import com.revature.util.ValidatorMessageUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @Email( message = ValidatorMessageUtil.EMAIL_REQUIRED_ON_CREATE)
    @NotNull( message = ValidatorMessageUtil.EMAIL_REQUIRED)
    private String email;

    @NotNull( message = ValidatorMessageUtil.PASSWORD_REQUIRED_ON_CREATE)
    @Pattern( regexp  = Regex.PASSWORD, message = ValidatorMessageUtil.PASSWORD_REQUIREMENTS)
    private String password;

    @NotNull( message = ValidatorMessageUtil.FNAME_REQUIRED_ON_CREATE)
    @Length( min = 1, message = ValidatorMessageUtil.FNAME_REQUIRED_ON_CREATE)
    private String firstName;

    @NotNull( message = ValidatorMessageUtil.LNAME_REQUIRED_ON_CREATE)
    @Length ( min = 1, message = ValidatorMessageUtil.LNAME_REQUIRED_ON_CREATE)
    private String lastName;
}
