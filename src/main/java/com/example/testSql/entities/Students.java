package com.example.testSql.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@ToString
@Table(name = "student")
public class Students {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;


    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "school_name")
    private String schoolName;

    @Column(name = "phone_number")
    private Integer phoneNumber;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "students", cascade = CascadeType.REFRESH, orphanRemoval = true)
    private List<Project> projects = new ArrayList<>();


}