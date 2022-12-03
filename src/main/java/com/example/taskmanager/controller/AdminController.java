package com.example.taskmanager.controller;

import com.example.taskmanager.entity.User;
import com.example.taskmanager.reqres.GetUserResponse;
import com.example.taskmanager.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class AdminController {
    private UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    @ApiOperation("Получение пользователся по идентификатору")
    public ResponseEntity<GetUserResponse> getUserById(@PathVariable(name = "id") Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.noContent().build();
        }
        GetUserResponse getUserResponse = new GetUserResponse(user.getUsername(), user.getPassword());
        return ResponseEntity.ok(getUserResponse);
    }


}
