package com.example.testSql.services;


import com.example.testSql.entities.Students;
import com.example.testSql.exceptions.StudentIDAlreadyExistException;
import com.example.testSql.exceptions.StudentIDException;
import com.example.testSql.repositories.StudentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentsService {

    private final StudentsRepository studentsRepository;

    public void addNewStudent(Students student) {
        if (studentsRepository.existsById(student.getId())) {
            throw new StudentIDAlreadyExistException("Student ID '"+student.getId().toString()+"' already exist");
        } else {
            studentsRepository.save(student);
        }


    }

    public void updateStudent(Students student) {
        if (!studentsRepository.existsById(student.getId())) {
            throw  new StudentIDException("Student ID '"+student.getId().toString()+"' does not exist");
        } else {
            studentsRepository.save(student);
        }
    }

    public Iterable<Students> getAll() {
        return studentsRepository.findAll();
    }

    public Optional<Students> getStudentByID(UUID studentID) {
        if (studentsRepository.existsById(studentID)) {
            return studentsRepository.findById(studentID);
        } else {
            throw  new StudentIDException("Student ID '"+studentID.toString()+"' does not exist");
        }

    }

    public void deleteStudentByID(UUID studentID) {
        if (studentsRepository.existsById(studentID)) {
            studentsRepository.deleteById(studentID);
        } else {
            throw new StudentIDException("Student ID '"+studentID.toString()+"' does not exist");
        }

    }

    public Students getStudentByName(String name) {
        if (studentsRepository.existsByName(name)) {
            return studentsRepository.findByName(name);
        } else {
            throw new StudentIDException("The student '"+name+"' does not exist");
        }
    }

    public Integer getPhoneNumberByName(String name) {
        Students student = getStudentByName(name);
        return student.getPhoneNumber();
    }
}