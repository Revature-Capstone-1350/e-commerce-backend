package com.revature.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NotNull
    @NotBlank
    private String email;

    @Length(min = 4)
    @NotNull
    @NotBlank
    private String password;
}
