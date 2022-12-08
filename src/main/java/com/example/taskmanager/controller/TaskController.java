package com.example.taskmanager.controller;

import com.example.taskmanager.entity.Task;
import com.example.taskmanager.reqres.*;
import com.example.taskmanager.service.TaskService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation("Создать задачу")
    public ResponseEntity<CreateTaskResponse> createTask(@RequestBody CreateTaskRequest createTaskRequest) {
        Task newTask = taskService.createTask(createTaskRequest.getTitle(), createTaskRequest.isCompleted());
        CreateTaskResponse createTaskResponse = new CreateTaskResponse(newTask.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(createTaskResponse);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ApiOperation("Обновить задачу")
    public ResponseEntity<UpdateTaskResponse> updateTask(@PathVariable Long id, @RequestBody UpdateTaskRequest updateTaskRequest) {
        Task newTask = taskService.updateTask(id, updateTaskRequest.getTitle(), updateTaskRequest.isCompleted());
        UpdateTaskResponse updateTaskResponse = new UpdateTaskResponse(newTask.getId());
        return ResponseEntity.ok(updateTaskResponse);
    }

    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    @ApiOperation("Отфильтровать задачи по описанию")
    public ResponseEntity<ShowTaskResponse> FilterTaskByTitle(@RequestBody FilterRequest filterRequest) {
        Task newTask = taskService.getTaskByTitle(filterRequest.getFilter());
        ShowTaskResponse showTaskResponse = new ShowTaskResponse(newTask.isCompleted(), newTask.getTitle());
        return ResponseEntity.ok(showTaskResponse);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation("Получить задачу по идентификатору")
    public ResponseEntity<ShowTaskResponse> getTaskById(@PathVariable("id") Long id) {
        Task task = taskService.getTaskById(id);
        ShowTaskResponse showTaskResponse = new ShowTaskResponse(task.isCompleted(), task.getTitle());
        return ResponseEntity.ok(showTaskResponse);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ApiOperation("Получить весь список задач")
    public ResponseEntity<ShowTaskList> getAllTasks() {
        List<Task> taskList = taskService.getAllTasks();
        List<ShowTaskResponse> taskListResponse = new ArrayList<>();
        taskList.forEach(task -> taskListResponse.add(
                new ShowTaskResponse(task.isCompleted(),
                        task.getTitle()))
        );
        ShowTaskList showTaskList = new ShowTaskList(taskListResponse);
        return ResponseEntity.ok(showTaskList);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation("Удалить задачу")
    public void deleteTaskById(@PathVariable("id") Long id) {
        taskService.deleteTaskById(id);
    }
}
