package com.example.testSql.services;


import com.example.testSql.entities.Students;
import com.example.testSql.repositories.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentsService {
    @Autowired
    private StudentsRepository studentsRepository;

    public void addNewStudent(Students student) {
//        if (studentsRepository.existsByKpnID(student.getKpnID())) {
//            throw new RuntimeException("The student already exist");
//        } else {
//            studentsRepository.save(student);
//        }
        studentsRepository.save(student);

    }

    public void updateStudent(Students student) {
        if (studentsRepository.existsById(student.getId()) == false) {
            throw new RuntimeException("The student doesn't exist");
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
            throw new RuntimeException("The student doesn't exist");
        }
    }

    public void deleteStudentByID(UUID studentID) {
        if (studentsRepository.existsById(studentID)) {
            studentsRepository.deleteById(studentID);
        } else {
            throw new RuntimeException("The student doesn't exist");
        }

    }

    public Students getStudentByName(String name) {
        if (studentsRepository.existsByName(name)) {
            return studentsRepository.findByName(name);
        } else {
            throw new RuntimeException("The student doesn't exist");
        }
    }

    public Integer getPhoneNumberByName(String name) {
        Students student = getStudentByName(name);
        return student.getPhoneNumber();
    }
}