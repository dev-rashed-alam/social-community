package com.example.model;

import com.example.entity.Attachment;
import com.example.entity.Location;
import lombok.Data;

@Data
public class UserModal {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Location location;
    private Attachment attachment;
}
