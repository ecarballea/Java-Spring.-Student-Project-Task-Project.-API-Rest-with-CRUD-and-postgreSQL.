package com.example.testSql.controllers;

import com.example.testSql.entities.Project;
import com.example.testSql.entities.Students;
import com.example.testSql.exceptions.ProjectEmptyException;
import com.example.testSql.services.ProjectService;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("/new/{studentID}")
    public void addNewProject(@RequestBody @Nonnull Project project, @PathVariable UUID studentID){
        if (project == null){
            throw new IllegalArgumentException("Project is missing");
        } else {
            projectService.addNewProject(project, studentID);
        }

    }


    @GetMapping("/findAll")
    public Iterable<Project> getAllProjects(){

        Iterable<Project> projectList = projectService.getAll();
        if (((ArrayList) projectList).size() != 0) {
            return projectList;
        } else {
            throw new ProjectEmptyException("There are not any Project yet");
        }
    }

    @PostMapping("/find")
    public Optional<Project> getProject(@RequestBody @Nonnull Project project){
        Optional<Project> project_result;
        if (project.getId() != null){
            project_result = projectService.getProjectByID(project.getId());
        } else if (project.getProjectName() != null) {
            project_result = projectService.getProjectByName(project.getProjectName());
        } else {
            throw new IllegalArgumentException("Project Name or Project ID is missing");
        }

        return project_result;
    }

    @PutMapping("/update")
    public void updateProject(@RequestBody Project project){

        if (project == null) {
            throw new IllegalArgumentException("Project is missing");
        } else{
            projectService.updateProject(project);
        }
    }

    @DeleteMapping("/delete")
    public void deleteProject(@RequestBody Project project){

        projectService.deleteStudent(project);
    }

    @PostMapping("/getStudentID")
    public Optional<UUID> getStudentIDByProjectID(@RequestBody Project project){
        Optional<UUID> studentID_result;
        if (project == null){
            throw new IllegalArgumentException("Project is missing");
        } else {
            studentID_result = projectService.getStudentIDByProjectID(project);
        }
        return studentID_result;

    }

    // doesn't show the related student, this can possibly be related to @JsonIgnore in the project entity
//    @PostMapping("/getStudent")
//    public Optional<Students> getStudentByProjectID(@RequestBody Project project){
//        Optional<Students> students_result;
//        if (project == null){
//            throw new IllegalArgumentException("Project is missing");
//        } else {
//            students_result = projectService.getStudentByProjectID(project);
//        }
//        return students_result;
//
//    }
}
