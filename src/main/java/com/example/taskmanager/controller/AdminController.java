package com.example.taskmanager.controller;

import com.example.taskmanager.entity.Task;
import com.example.taskmanager.entity.User;
import com.example.taskmanager.reqres.*;
import com.example.taskmanager.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/users")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation("Получение пользователся по идентификатору")
    public ResponseEntity<GetUserResponse> getUserById(@PathVariable("id") Long id) {
        Optional<User> userOpt = userService.findById(id);
        if (userOpt.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        User user = userOpt.get();
        GetUserResponse getUserResponse = new GetUserResponse(user.getUsername(), user.getPassword());
        return ResponseEntity.ok(getUserResponse);
    }

    @RequestMapping(value = "/{id}/tasks", method = RequestMethod.GET)
    @ApiOperation("Получить список задач пользователя")
    public ResponseEntity<GetTasksResponse> getUserTasks(@PathVariable("id") Long id) {
        Optional<User> userOpt = userService.findById(id);
        if (userOpt.isPresent()) {
            return getShowTaskListResponseEntity(userOpt.get());
        } else return ResponseEntity.noContent().build();

}

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ApiOperation("Добавление задачи пользователю по индентификатору")
    public ResponseEntity<HttpStatus> addTaskToUser(@RequestBody AddTaskToUserRequest addTaskToUserRequest, @PathVariable("id") Long id) {
        userService.addTask(id, addTaskToUserRequest.getId());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation("Удаление задачи из списка дел пользователя по индентификатору")
    public ResponseEntity<HttpStatus> deleteTaskFromUser(@RequestBody DeleteTaskFromUserRequest deleteTaskFromUserRequest, @PathVariable("id") Long id) {
        userService.deleteTask(id, deleteTaskFromUserRequest.getId());
        return ResponseEntity.ok(HttpStatus.OK);
    }


    private ResponseEntity<GetTasksResponse> getShowTaskListResponseEntity(User user) {
        List<Task> taskList = user.getTasks();
        List<GetTaskResponse> taskResponseList = new ArrayList<>();
        taskList.forEach(task ->
                taskResponseList.add(new GetTaskResponse(task.isCompleted(), task.getTitle())));
        GetTasksResponse showTaskLists = new GetTasksResponse(taskResponseList);
        return ResponseEntity.ok(showTaskLists);
    }


}
