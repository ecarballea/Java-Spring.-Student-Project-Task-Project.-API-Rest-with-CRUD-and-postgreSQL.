package com.example.testSql.controllers;

import com.example.testSql.entities.Project;
import com.example.testSql.entities.Students;
import com.example.testSql.entities.Task;
import com.example.testSql.services.TaskService;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/task")
@CrossOrigin
public class TaskController {
    @Autowired
    public TaskService taskService;

    @PostMapping("/new/{projectID}")
    public void addNewTask(@RequestBody @Nonnull Task task, @PathVariable UUID projectID){
        if (task == null){
            throw new IllegalArgumentException("Task is missing");
        } else {
            taskService.addNewTask(task, projectID);
        }

    }

    @GetMapping("/findAll")
    public Iterable<Task> getAllTasks(){

        Iterable<Task> tasksList = taskService.getAll();
        if (((ArrayList)tasksList).size() != 0) {
            return tasksList;
        } else {
            return null;
        }
    }

    @PostMapping("/find")
    public Optional<Task> getTaskByID(@RequestBody @Nonnull Task task){
        Optional<Task> task_result;
        if (task.getId() != null){
            task_result = taskService.getTaskByID(task.getId());
        } else {
            throw new IllegalArgumentException("Student is missing");
        }


        return task_result;
    }

    @PutMapping("/update")
    public void updateTask(@RequestBody Task task){
        if (task == null) {
            throw new IllegalArgumentException("Task is missing");
        } else{
            taskService.updateTask(task);
        }
    }

    @DeleteMapping("/delete")
    public void deleteTask(@RequestBody Task task){

        taskService.deleteTask(task);
    }
}
