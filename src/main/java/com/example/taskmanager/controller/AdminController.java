package com.example.taskmanager.controller;

import com.example.taskmanager.entity.Task;
import com.example.taskmanager.entity.User;
import com.example.taskmanager.reqres.AddTaskToUserRequest;
import com.example.taskmanager.reqres.GetUserResponse;
import com.example.taskmanager.reqres.ShowTaskList;
import com.example.taskmanager.reqres.ShowTaskResponse;
import com.example.taskmanager.service.TaskService;
import com.example.taskmanager.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class AdminController {
    private final UserService userService;
    private final TaskService taskService;

    public AdminController(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation("Получение пользователся по идентификатору")
    public ResponseEntity<GetUserResponse> getUserById(@PathVariable("id") Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.noContent().build();
        }
        GetUserResponse getUserResponse = new GetUserResponse(user.getUsername(), user.getPassword());
        return ResponseEntity.ok(getUserResponse);
    }

    @RequestMapping(value = "/{id}/tasks", method = RequestMethod.GET)
    @ApiOperation("Получить список задач пользователя")
    public ResponseEntity<ShowTaskList> getUserTasks(@PathVariable("id") Long id) {
        User user = userService.findById(id);
        return getShowTaskListResponseEntity(user);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ApiOperation("Добавление задачи пользователю по индентификатору")
    public ResponseEntity<ShowTaskList> addTaskToUser(@RequestBody AddTaskToUserRequest addTaskToUserRequest, @PathVariable("id") Long id){
        User user=userService.findById(id);
        Task task=taskService.getTaskById(addTaskToUserRequest.getId());
        userService.addTask(user.getTasks(),task);
        return getShowTaskListResponseEntity(user);
    }

    private ResponseEntity<ShowTaskList> getShowTaskListResponseEntity(User user) {
        List<Task> taskList=user.getTasks();
        List<ShowTaskResponse> taskResponseList=new ArrayList<>();
        taskList.forEach(task ->
                taskResponseList.add(new ShowTaskResponse(task.isCompleted(),task.getTitle())));
        ShowTaskList showTaskLists=new ShowTaskList(taskResponseList);
        return ResponseEntity.ok(showTaskLists);
    }

}
