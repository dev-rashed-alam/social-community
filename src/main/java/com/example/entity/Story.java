package com.example.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tbl_story")
public class Story implements Serializable {
    @Id
    @Column(name = "st_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

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

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "story_attachments", joinColumns = {@JoinColumn(name = "story_id", referencedColumnName = "st_id")}, inverseJoinColumns = {@JoinColumn(name = "attachment_id", referencedColumnName = "at_id")})
    private List<Attachment> storyAttachments = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

}
