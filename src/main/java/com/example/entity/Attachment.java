package com.example.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "tbl_attachment")
public class Attachment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "attachment_name")
    private String attachmentName;

    @Column(name = "attachment_path")
    private String attachmentPath;

    @Column(name = "type")
    private String type;

}
