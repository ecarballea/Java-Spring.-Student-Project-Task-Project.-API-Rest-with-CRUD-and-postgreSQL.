package com.example.testSql.controllers;


import com.example.testSql.entities.Project;
import com.example.testSql.entities.Students;
import com.example.testSql.exceptions.StudentDoesNotExistException;
import com.example.testSql.exceptions.StudentIDException;
import com.example.testSql.services.ProjectService;
import com.example.testSql.services.StudentsService;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
@CrossOrigin
public class StudentsController {

    @Autowired
    private  StudentsService studentsService;

    @Autowired
    private ProjectService projectService;

    @PostMapping("/new")
    public ResponseEntity<?> addNewStudent(@RequestBody @Nonnull Students student){
        if (student == null) {
            throw new IllegalArgumentException("Student is missing");
        } else{
            Students createdStudent = studentsService.addNewStudent(student);
            return new ResponseEntity<Students>(createdStudent, HttpStatus.CREATED);
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

    @PostMapping("/findID")
    public Optional<Students> getStudentByID(@RequestBody @Nonnull Students student){
        Optional<Students> student_result;
        if (student.getId() != null){
            student_result = studentsService.getStudentByID(student.getId());
        } else {
            throw new IllegalArgumentException("Student is missing");
        }

        return student_result;
    }
    @PostMapping("/find")
    public Optional<List<Students>> getStudentBy(@RequestBody @Nonnull Students student){
        Optional<List<Students>> student_result;
        if (student.getName() != null || student.getSchoolName() != null || student.getAge() != null) {
            student_result = studentsService.getStudentsBy(student);
        } else {
            throw new StudentDoesNotExistException("Student is missing");
        }

        return student_result;
    }

    @PutMapping("/update")
    public Students updateStudent(@RequestBody Students student){
        if (student == null) {
            throw new IllegalArgumentException("Student is missing");
        } else{
            return studentsService.updateStudent(student);
        }
    }

    @DeleteMapping("/delete")
    public void deleteStudent(@RequestBody Students student){

        studentsService.deleteStudent(student);
    }

    @PostMapping("/getProjects")
    public List<Project> getProjectsByStudentID(@RequestBody Students student){
        if (student.getId() == null){
            throw new StudentIDException("The Student ID is missing in the Body");
        } else {
            return studentsService.getProjectsByStudentID(student);
        }
    }

}
