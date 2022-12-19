package com.example.taskmanager.controller;

import com.example.taskmanager.entity.User;
import com.example.taskmanager.reqres.RegisterUserRequest;
import com.example.taskmanager.reqres.RegisterUserResponse;
import com.example.taskmanager.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registration")
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation("Регистрация пользователя")
    public ResponseEntity<RegisterUserResponse> registerUser(@RequestBody RegisterUserRequest registerUserRequest) {
        User newUser = userService.register(registerUserRequest.getUsername(), registerUserRequest.getPassword());
        RegisterUserResponse registerUserResponse = new RegisterUserResponse(newUser.getId());
        return ResponseEntity.ok(registerUserResponse);
    }
}
