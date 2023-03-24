package com.example.testSql.services;

import com.example.testSql.entities.Project;
import com.example.testSql.entities.Students;
import com.example.testSql.exceptions.ProjectIDException;
import com.example.testSql.repositories.ProjectRepository;
import com.example.testSql.repositories.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProjectService {
    @Autowired
    private  ProjectRepository projectRepository;

    @Autowired
    private StudentsRepository studentsRepository;


    public void addNewProject(Project project, UUID studentID) {
        Optional<Students> existingStudent = studentsRepository.findById(studentID);
        if (existingStudent != null){
            project.setStudents(existingStudent.get());
        }

        projectRepository.save(project);
    }

    public Iterable<Project> getAll() {
        return projectRepository.findAll();
    }

    public Optional<Project> getProjectByID(UUID id) {
        return projectRepository.findById(id);
    }

    public Optional<Project> getProjectByName(String projectName) {
        return projectRepository.findByProjectName(projectName);
    }

    public void updateProject(Project project) {
        if (!projectRepository.existsById(project.getId())) {
            throw  new ProjectIDException("Project ID '"+project.getId().toString()+"' does not exist");
        } else {
            projectRepository.save(project);
        }
    }

    public void deleteStudent(Project project) {
        if (projectRepository.existsById(project.getId())) {
            projectRepository.deleteById(project.getId());
        } else {
            throw new ProjectIDException("Project ID '"+project.getId().toString()+"' does not exist");
        }
    }

    public Optional<Students> getStudentByProjectID(Project project) {
        Optional<Students> studentResult;
        Optional<Project> existingProject = projectRepository.findById(project.getId());
        if (existingProject == null){
            throw new ProjectIDException("The Project ID " + project.getId() + " does not exist");
        } else {
            UUID studentID = existingProject.get().getStudents().getId();
            studentResult = studentsRepository.findById(studentID);
            if (studentResult == null){
                throw new ProjectIDException("The related Student with ID " + studentID + " is not exists");
            }
        }

        return studentResult;
    }

    public Optional<UUID> getStudentIDByProjectID(Project project) {
        Optional<UUID> studentIDResult;
        Optional<Project> existingProject = projectRepository.findById(project.getId());
        if (existingProject == null){
            throw new ProjectIDException("The Project ID " + project.getId() + " is not exists");
        } else {
            UUID studentID = existingProject.get().getStudents().getId();
            studentIDResult = Optional.ofNullable(studentID);

        }

        return studentIDResult;
    }
}
