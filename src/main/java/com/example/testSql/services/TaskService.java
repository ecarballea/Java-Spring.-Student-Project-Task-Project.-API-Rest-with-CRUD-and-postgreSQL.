package com.example.testSql.services;

import com.example.testSql.entities.Project;
import com.example.testSql.entities.Students;
import com.example.testSql.entities.Task;
import com.example.testSql.exceptions.StudentIDException;
import com.example.testSql.exceptions.TaskIDException;
import com.example.testSql.repositories.ProjectRepository;
import com.example.testSql.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    public TaskRepository taskRepository;
    @Autowired
    private ProjectRepository projectRepository;

    public void addNewTask(Task task, UUID taskID) {
        Optional<Project> existingTask = projectRepository.findById(taskID);
        if (existingTask != null){
            task.setProject(existingTask.get());
        }

        taskRepository.save(task);
    }

    public Iterable<Task> getAll() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskByID(UUID id) {
        if (taskRepository.existsById(id)) {
            return taskRepository.findById(id);
        } else {
            throw  new TaskIDException("Task ID '"+id.toString()+"' does not exist");
        }
    }

    public void updateTask(Task task) {
        if (!taskRepository.existsById(task.getId())) {
            throw  new TaskIDException("Task ID '"+task.getId().toString()+"' does not exist");
        } else {
            taskRepository.save(task);
        }
    }

    public void deleteTask(Task task) {
        if (taskRepository.existsById(task.getId())) {
            taskRepository.deleteById(task.getId());
        } else {
            throw new TaskIDException("Task ID '"+task.getId().toString()+"' does not exist");
        }
    }
}
