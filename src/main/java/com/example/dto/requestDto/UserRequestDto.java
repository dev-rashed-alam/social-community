package com.example.dto.requestDto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserRequestDto {
    private Long id;

    @NotBlank(message = "Name Required!")
    private String name;

    @NotBlank(message = "Email Required!")
    @Email
    private String email;

    @NotBlank(message = "Password Required!")
    private String password;

    private Long locationId;
}
