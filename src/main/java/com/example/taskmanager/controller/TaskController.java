package com.example.taskmanager.controller;

import com.example.taskmanager.entity.Task;
import com.example.taskmanager.service.TaskService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@Tag(name = "TaskController", description = "Controller for tasks")
public class TaskController {
    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation("Create task")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task newTask = taskService.createTask(task.getTitle(), task.isCompleted());
        return ResponseEntity.status(HttpStatus.CREATED).body(newTask);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ApiOperation("Update task")
    public ResponseEntity<Task> updateTask(@RequestBody Task task) {
        Task newTask = taskService.updateTask(task.getId(), task.getTitle(), task.isCompleted());
        return ResponseEntity.ok(newTask);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ApiOperation("Get task by Id")
    public ResponseEntity<Task> getTaskById(@PathVariable("id") Long id) {
        Task task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }
    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation("Get all tasks")
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ApiOperation("Delete task")
    public void deleteTaskById(@PathVariable("id") Long id) {
        taskService.deleteTaskById(id);
    }
}
