package com.revature.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NotBlank // cannot be like ""
    @NotNull // cannot be missing
    private String email;

    @NotBlank // cannot be like ""
    @NotNull // cannot be missing
    private String password;
}
