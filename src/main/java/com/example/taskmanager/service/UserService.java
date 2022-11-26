package com.example.taskmanager.service;

import com.example.taskmanager.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    User register(User user);

    List<User> getAll();

    User findByUsername(String username);

    User findById(Long id);

    void deleteUser(Long id);
}
