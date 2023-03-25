package com.example.testSql.repositories;

import com.example.testSql.entities.Students;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentsRepository extends CrudRepository<Students, UUID> {
    @Query("select s from Students s where s.name = ?1 and s.age = ?2")
    List<Students> findByNameAndAge(String name, Integer age);
    @Query("select s from Students s where s.name = ?1 and s.schoolName = ?2")
    List<Students> findByNameAndSchoolName(String name, String schoolName);
    @Query("select s from Students s where s.age = ?1")
    List<Students> findByAge(Integer age);
    @Query("select s from Students s where s.schoolName = ?1")
    List<Students> findBySchoolName(String schoolName);
    @Query("select s from Students s where s.age = ?1 and s.schoolName = ?2")
    List<Students> findByAgeAndSchoolName(Integer age, String schoolName);
    @Query("select s from Students s where s.name = ?1 or s.age = ?2 or s.schoolName = ?3")
    List<Students> findByNameOrAgeOrSchoolName(@Nullable String name, @Nullable Integer age, @Nullable String schoolName);
    @Query("select s from Students s where s.name = ?1 and s.age = ?2 and s.schoolName = ?3")
    List<Students> findByNameAndAgeAndSchoolName(@Nullable String name, @Nullable Integer age, @Nullable String schoolName);
    @Query("select s from Students s where s.age = ?1 and s.name = ?2 order by s.schoolName")
    List<Students> findByAgeAndNameOrderBySchoolNameAsc(Integer age, String name);
    @Query("select s from Students s where s.schoolName = ?1 and s.age = ?2 order by s.name")
    List<Students> findBySchoolNameAndAgeOrderByNameAsc(String schoolName, Integer age);
    @Query("select s from Students s where s.age = ?1 order by s.name")
    List<Students> findByAgeOrderByNameAsc(Integer age);
    @Query("select s from Students s where s.schoolName = ?1 order by s.name")
    List<Students> findBySchoolNameOrderByNameAsc(String schoolName);
    @Query("select (count(s) > 0) from Students s where s.name = ?1")
    boolean existsByName(String name);
    List<Students> findByName(String name);

    @Override
    Optional<Students> findById(UUID uuid);

    @Override
    Iterable<Students> findAll();




}
