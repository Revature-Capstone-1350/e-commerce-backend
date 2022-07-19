package com.revature.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResetRequest {
    @NotBlank // cannot be like ""
    @NotNull // cannot be missing
    private String oldPassword;

    @NotBlank // cannot be like ""
    @NotNull // cannot be missing
    private String newPassword;
}
