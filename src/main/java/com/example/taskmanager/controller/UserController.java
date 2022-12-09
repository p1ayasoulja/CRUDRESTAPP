package com.example.taskmanager.controller;

import com.example.taskmanager.entity.Task;
import com.example.taskmanager.entity.User;
import com.example.taskmanager.repository.TaskRepo;
import com.example.taskmanager.repository.UserRepo;
import com.example.taskmanager.reqres.ShowTaskList;
import com.example.taskmanager.reqres.ShowTaskResponse;
import com.example.taskmanager.service.TaskService;
import com.example.taskmanager.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final TaskService taskService;

    public UserController(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

//    @RequestMapping(value = "/mytasks",method = RequestMethod.GET)
//    public ResponseEntity<ShowTaskList> getMyTasks(){
//        List<Task> taskList = taskService.getTasksByOwner();
//        List<ShowTaskResponse> taskListResponse = new ArrayList<>();
//        taskList.forEach(task -> taskListResponse.add(
//                new ShowTaskResponse(task.isCompleted(),
//                        task.getTitle()))
//        );
//        ShowTaskList showTaskList = new ShowTaskList(taskListResponse);
//        return ResponseEntity.ok(showTaskList);
//    }
//    }


}
