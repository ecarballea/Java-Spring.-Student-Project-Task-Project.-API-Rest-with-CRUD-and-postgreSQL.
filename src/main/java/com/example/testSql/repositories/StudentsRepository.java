package com.example.testSql.repositories;

import com.example.testSql.entities.Students;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentsRepository extends CrudRepository<Students, UUID> {
    @Query("select (count(s) > 0) from Students s where s.name = ?1")
    boolean existsByName(String name);
    Students findByName(String name);

    @Override
    Optional<Students> findById(UUID uuid);

    @Override
    Iterable<Students> findAll();


}
