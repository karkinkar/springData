package com.example.demo.adapters.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="userdata")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private Integer height;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    private Address address;
}
