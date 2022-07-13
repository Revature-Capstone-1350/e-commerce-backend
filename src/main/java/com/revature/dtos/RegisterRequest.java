package com.revature.dtos;

import com.revature.util.ValidatorMessageUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @Email(message= ValidatorMessageUtil.EMAIL_REQUIREMENTS)
    @NotNull(message = ValidatorMessageUtil.EMAIL_REQUIRED_ON_CREATE)
    private String email;

    @Length(min=5, message=ValidatorMessageUtil.PASSWORD_REQUIREMENTS)
    private String password;

    @NotBlank(message = ValidatorMessageUtil.FIRST_NAME_REQUIRED_ON_CREATE)
    private String firstName;

    private String lastName;
}
