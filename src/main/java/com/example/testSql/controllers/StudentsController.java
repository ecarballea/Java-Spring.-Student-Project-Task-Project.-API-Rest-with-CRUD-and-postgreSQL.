package com.example.testSql.controllers;


import com.example.testSql.entities.Students;
import com.example.testSql.services.StudentsService;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/students")
@CrossOrigin
public class StudentsController {

    @Autowired
    private  StudentsService studentsService;

    @PostMapping("/new")
    public void addNewStudent(@RequestBody @Nonnull Students student){
        if (student == null) {
            throw new IllegalArgumentException("Student is missing");
        } else{
            studentsService.addNewStudent(student);
        }
    }

    @GetMapping("/findAll")
    public Iterable<Students> getAllStudents(){

        Iterable<Students> studentsList = studentsService.getAll();
        if (((ArrayList)studentsList).size() != 0) {
            return studentsList;
        } else {
            return null;
        }
    }

    @PostMapping("/find")
    public Optional<Students> getStudentByID(@RequestBody @Nonnull Students student){
        Optional<Students> student_result;
        if (student.getId() != null){
            student_result = studentsService.getStudentByID(student.getId());
        } else if (student.getName() != null) {
            student_result = studentsService.getStudentByName(student.getName());
        } else {
            throw new IllegalArgumentException("Student is missing");
        }


        return student_result;
    }

//    @PostMapping("/find")
//    public Students getStudentByName(@RequestBody @PathVariable String name){
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

    @DeleteMapping("/delete")
    public void deleteStudent(@RequestBody Students student){

        studentsService.deleteStudent(student);
    }

}
