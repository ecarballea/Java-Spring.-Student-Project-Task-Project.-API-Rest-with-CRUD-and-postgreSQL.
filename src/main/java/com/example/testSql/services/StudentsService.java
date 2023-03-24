package com.example.testSql.services;


import com.example.testSql.entities.Students;
import com.example.testSql.exceptions.StudentIDAlreadyExistException;
import com.example.testSql.exceptions.StudentIDException;
import com.example.testSql.repositories.StudentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
//@RequiredArgsConstructor
public class StudentsService {

    @Autowired
    private StudentsRepository studentsRepository;

    public void addNewStudent(Students student) {
        studentsRepository.save(student);

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

    public void deleteStudent(Students student) {
        if (studentsRepository.existsById(student.getId())) {
            studentsRepository.deleteById(student.getId());
        } else {
            throw new StudentIDException("Student ID '"+student.getId().toString()+"' does not exist");
        }

    }

    public Optional<Students> getStudentByName(String name) {

        Optional<Students> student_result;
        if (studentsRepository.existsByName(name)) {
            student_result =  studentsRepository.findByName(name);
        } else {
            throw new StudentIDException("The student '"+name+"' does not exist");
        }
        return student_result;
    }

    public Integer getPhoneNumberByName(String name) {
        Optional<Students> student = getStudentByName(name);
        return student.get().getPhoneNumber();
    }
}