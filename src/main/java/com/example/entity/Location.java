package com.example.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tbl_location")
public class Location implements Serializable {
    @Id
    @Column(name = "loc_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "location_name")
    private String locationName;

    @OneToMany(orphanRemoval = true, mappedBy = "location")
    private List<User> users = new ArrayList<>();
}
