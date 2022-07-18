package com.revature.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotBlank // This works. Blank input causes MethodArgumentNotValidException
    @NotNull
    private String email;

    @NotBlank // requires that the field isn't just "" (zero characters)
    @NotNull // requires field to be in the response body
    private String password;

    @NotBlank
    @NotNull
    private String firstName;

    private String lastName;
}
