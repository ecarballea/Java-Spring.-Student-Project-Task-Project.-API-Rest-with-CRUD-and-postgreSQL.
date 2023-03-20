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
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;


    @Column(name = "task_name")
    private String taskName;

    @Column(name = "number")
    private Integer number;



    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "project_id")
    private Project project;

}