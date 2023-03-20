package com.example.testSql.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@ToString
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;


    @Column(name = "project_name")
    private String projectName;

    @Column(name = "start")
    private Instant start;

    @Column(name = "end")
    private String end;


    @OneToMany(mappedBy = "project", cascade = CascadeType.REFRESH, orphanRemoval = true)
    private List<Task> tasks = new ArrayList<>();

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "students_id")
    private Students students;
}