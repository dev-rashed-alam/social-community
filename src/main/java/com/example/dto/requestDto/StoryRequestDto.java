package com.example.dto.requestDto;

import lombok.Data;

@Data
public class StoryRequestDto {
    private Long id;
    private String title;
    private String description;
    private Long locationId;
}
