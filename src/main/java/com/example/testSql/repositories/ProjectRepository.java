package com.example.testSql.repositories;

import com.example.testSql.entities.Project;
import com.example.testSql.entities.Students;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProjectRepository extends CrudRepository<Project, UUID> {
    @Query("select p from Project p where p.students.id = ?1")
    Students findByStudents_Id(UUID id);
    @Query("select p from Project p where p.projectName = ?1")
    Optional<Project> findByProjectName(String projectName);

    @Override
    void deleteById(UUID uuid);
}