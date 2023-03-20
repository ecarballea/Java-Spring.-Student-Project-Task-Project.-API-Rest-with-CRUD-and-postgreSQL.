package com.example.testSql.controllers;


import com.example.testSql.entities.Students;
import com.example.testSql.services.StudentsService;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
@CrossOrigin
public class StudentsController {

    private final StudentsService studentsService;

    @PostMapping("/create")
    public void addNewStudent(@RequestBody Students student){
        if (student == null) {
            throw new IllegalArgumentException("Student is missing");
        } else{
            studentsService.addNewStudent(student);
        }
    }

    @GetMapping("/read")
    public Iterable<Students> getAllStudents(){

        Iterable<Students> studentsList = studentsService.getAll();
        if (studentsList != null) {
            return studentsList;
        } else {
            return null;
        }
    }

    @GetMapping("/read/{studentID}")
    public Optional<Students> getStudentByID(@Nonnull @PathVariable UUID studentID){
        Optional<Students> student = studentsService.getStudentByID(studentID);
        return student;
    }

//    @GetMapping("/read/{name}")
//    public Students getStudentByName(@Nonnull @PathVariable String name){
//        Students student = studentsService.getStudentByName(name);
//        return student;
//    }

//    @GetMapping("/read/{name}")
//    public Integer getPhoneNumberByName(@PathVariable String name){
//        Integer phoneNumber = studentsService.getPhoneNumberByName(name);
//        return phoneNumber;
//    }

    @PutMapping("/update")
    public void updateStudent(@RequestBody Students student){
        if (student == null) {
            throw new IllegalArgumentException("Student is missing");
        } else{
            studentsService.updateStudent(student);
        }
    }

    @DeleteMapping("/delete/{studentID}")
    public void deleteStudentByID(@PathVariable UUID studentID){
        studentsService.deleteStudentByID(studentID);
    }

}
