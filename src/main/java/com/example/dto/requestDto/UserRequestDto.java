package com.example.dto.requestDto;

import lombok.Data;

@Data
public class UserRequestDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Long locationId;
}
