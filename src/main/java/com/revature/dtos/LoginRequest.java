package com.revature.dtos;

import com.revature.util.ValidatorMessageUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @Email(message= ValidatorMessageUtil.EMAIL_REQUIREMENTS)
    private String email;

    @Length(min=5, message=ValidatorMessageUtil.PASSWORD_REQUIREMENTS)
    private String password;
}
