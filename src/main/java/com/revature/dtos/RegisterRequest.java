package com.revature.dtos;

import com.revature.utils.RegexUtil;
import com.revature.utils.ValidatorMessages;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @Email( message = ValidatorMessages.EMAIL_REQUIRED)
    @NotNull( message = ValidatorMessages.EMAIL_REQUIREMENTS)
    private String email;

    @NotNull( message = ValidatorMessages.PASSWORD_REQUIRED)
    @Pattern( regexp  = RegexUtil.PASSWORD, message = ValidatorMessages.PASSWORD_REQUIREMENTS)
    private String password;

    @NotNull( message = ValidatorMessages.FNAME_REQUIRED_ON_REGISTRATION)
    @Length ( min = 1, message = ValidatorMessages.FNAME_REQUIREMENT)
    private String firstName;

    @NotNull( message = ValidatorMessages.LNAME_REQUIRED_ON_REGISTRATION)
    @Length ( min = 1, message = ValidatorMessages.FNAME_REQUIREMENT)

    private String lastName;
}
