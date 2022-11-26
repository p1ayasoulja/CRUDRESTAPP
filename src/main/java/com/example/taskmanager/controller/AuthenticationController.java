package com.example.taskmanager.controller;

import com.example.taskmanager.Security.jwt.JwtTokenProvider;
import com.example.taskmanager.entity.User;
import com.example.taskmanager.reqres.LoginUserRequest;
import com.example.taskmanager.reqres.LoginUserResponse;
import com.example.taskmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class AuthenticationController {

    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private UserService userService;

    @Autowired
    public void setLoginController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody LoginUserRequest loginUserRequest) {
        try {
            String username=loginUserRequest.getUsername();
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, loginUserRequest.getPassword()));
            User user = userService.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("User with username : " + username + " not found");
            }
            String token = jwtTokenProvider.createToken(user.getUsername(), user.getTasks());

            LoginUserResponse loginUserResponse = new LoginUserResponse(username, token);
            return ResponseEntity.ok(loginUserResponse);

        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }

    }
//    @RequestMapping(value = "/register",method = RequestMethod.POST)
//    public ResponseEntity register(@RequestBody UserRequest userRequest) {
//        User newUser=userService.register(userRequest);
//    }
}
