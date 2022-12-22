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
import java.util.Optional;

@RestController
@RequestMapping("/manager/tasks")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation("Создать задачу")
    public ResponseEntity<CreateTaskResponse> createTask2(@RequestBody CreateTaskRequest createTaskRequest) {
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

//    @RequestMapping(value = "/filter", method = RequestMethod.GET)
//    @ApiOperation("Отфильтровать задачи по описанию")
//    public ResponseEntity<GetTaskResponse> filterTaskByTitle(@RequestBody FilterTaskByTitleRequest filterTaskByTitleRequest) {
//        Task newTask = taskService.getTaskByTitle(filterTaskByTitleRequest.getTitle());
//        GetTaskResponse getTaskResponse = new GetTaskResponse(newTask.isCompleted(), newTask.getTitle());
//        return ResponseEntity.ok(getTaskResponse);
//    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation("Получить задачу по идентификатору")
    public ResponseEntity<GetTaskResponse> getTaskById(@PathVariable("id") Long id) {
        Optional<Task> task = taskService.get(id);
        if (task.isPresent()) {
            GetTaskResponse getTaskResponse = new GetTaskResponse(task.get().isCompleted(), task.get().getTitle());
            return ResponseEntity.ok(getTaskResponse);
        } else return ResponseEntity.notFound().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation("Получить весь список задач или отфильтрованный список по описанию")
    public ResponseEntity<GetTasksResponse> getAllTasks(@RequestBody(required = false)
                                                        FilterTaskByTitleRequest filterTaskByTitleRequest) {
        List<Task> taskList = taskService.getTasksByTitle(filterTaskByTitleRequest.getTitle());
        List<GetTaskResponse> taskListResponse = new ArrayList<>();
        taskList.forEach(task -> taskListResponse.add(
                new GetTaskResponse(task.isCompleted(),
                        task.getTitle()))
        );


        GetTasksResponse showTaskList = new GetTasksResponse(taskListResponse);
        return ResponseEntity.ok(showTaskList);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation("Удалить задачу")
    public void deleteTaskById(@PathVariable("id") Long id) {
        taskService.deleteTaskById(id);
    }

    @RequestMapping(value = "/complete/{id}", method = RequestMethod.PUT)
    @ApiOperation("Изменить статус выполнения задачи")
    public ResponseEntity<ChangeTaskCompleteResponse> changeTaskComplete(@PathVariable Long id) {
        Task newTask = taskService.changeTaskComplete(id);
        ChangeTaskCompleteResponse changeTaskCompleteResponse = new ChangeTaskCompleteResponse(newTask.isCompleted());
        return ResponseEntity.ok(changeTaskCompleteResponse);
    }
}
