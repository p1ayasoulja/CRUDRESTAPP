package com.example.taskmanager.controller;

import com.example.taskmanager.entity.User;
import com.example.taskmanager.reqres.LoginRegisterUserRequest;
import com.example.taskmanager.reqres.RegisterUserResponse;
import com.example.taskmanager.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registration")
public class RegistrationController {
    private UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<RegisterUserResponse> RegisterUser(@RequestBody LoginRegisterUserRequest registerUserRequest) {
        User newUser = userService.register(registerUserRequest.getUsername(), registerUserRequest.getPassword());
        RegisterUserResponse registerUserResponse = new RegisterUserResponse(newUser.getUsername());
        return ResponseEntity.ok(registerUserResponse);
    }
}
