package com.example.testSql.services;


import com.example.testSql.entities.Project;
import com.example.testSql.entities.Students;
import com.example.testSql.exceptions.*;
import com.example.testSql.repositories.ProjectRepository;
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
    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> getProjectsByStudentID(Students student) {
        Optional<Students> existingStudent = studentsRepository.findById(student.getId());
        List<Project> projectList;
        if (existingStudent.isEmpty()){
            // If the student does not exist throw an exception
            throw new StudentIDException("The Student ID " + student.getId() + " does not exist");
        } else {
            // return the List of the related Projects
            projectList = existingStudent.get().getProjects();
            if ( projectList.size() == 0){
                //If the Project List is empty throw an exception
                throw new ProjectEmptyException("There are any related Projects to the Student ID " + student.getId());
            }
        }

        return projectList;
    }

    public Students addNewStudent(Students student) {
        Students createdStudent = studentsRepository.save(student);
        return createdStudent;
    }

    public Students updateStudent(Students student) {
        if (!studentsRepository.existsById(student.getId())) {
            throw  new StudentIDException("Student ID '"+student.getId().toString()+"' does not exist");
        } else {
            Students updatedStudent = studentsRepository.save(student);
            return updatedStudent;
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


//    public Integer getPhoneNumberByName(String name) {
//        Optional<Students> student = getStudentByName(name);
//        return student.get().getPhoneNumber();
//    }

    public Optional<List<Students>> getStudentsBy(Students student) {
        List<Students> studentResult;
        // Name and Age and School Name
        if (student.getName() != null &&
                student.getAge() != null &&
                student.getSchoolName() != null){
            studentResult = studentsRepository.findByNameAndAgeAndSchoolName(student.getName(), student.getAge(), student.getSchoolName());
        // Name and Age
        } else if (student.getName() != null &&
                student.getAge() != null){
            studentResult = studentsRepository.findByNameAndAge(student.getName(), student.getAge());
        // Age and School Name
        } else if (student.getAge() != null &&
                student.getSchoolName() != null){
            studentResult = studentsRepository.findByAgeAndSchoolName(student.getAge(), student.getSchoolName());
        // Name and School Name
        } else if (student.getName() != null &&
                student.getSchoolName() != null) {
            studentResult = studentsRepository.findByNameAndSchoolName(student.getName(), student.getSchoolName());
        // only School Name
        } else if (student.getSchoolName() != null){
            studentResult = studentsRepository.findBySchoolName(student.getSchoolName());
        // only Age
        } else if (student.getAge() != null) {
            studentResult = studentsRepository.findByAge(student.getAge());
        // only Name
        } else if (student.getName() != null) {
            studentResult = studentsRepository.findByName(student.getName());
        // nothing
        } else {
            throw new StudentListEmptyException("There are any Student to show");
        }

        return Optional.ofNullable(studentResult);
    }
}