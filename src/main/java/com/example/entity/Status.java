package com.example.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tbl_status")
public class Status implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "description")
    private String description;

    @Column(name = "privacy")
    private String privacy;

    @Column(name = "location_id")
    private Long locationId;

    @Column(name = "created_date_time")
    private LocalDateTime createdDateTime;

    @Column(name = "is_deleted")
    private int isDeleted;


}
