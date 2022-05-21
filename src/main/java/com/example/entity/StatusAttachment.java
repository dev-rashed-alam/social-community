package com.example.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "tbl_status_attachment")
public class StatusAttachment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "status_id")
    private Long statusId;

    @Column(name = "attachment_id")
    private Long attachmentId;


}
