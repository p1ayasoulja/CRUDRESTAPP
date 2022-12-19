package com.example.taskmanager.service;

import com.example.taskmanager.entity.Task;
import com.example.taskmanager.entity.User;

import java.util.List;
import java.util.Optional;


public interface UserService {
    User register(String username, String password);

    List<User> getAll();

   Optional<User> findByUsername(String username);

    Optional<User> findById(Long id);

    void deleteUser(Long id);

    Task addTask(Long userid, Long taskid);

    void deleteTask(Long userid, Long taskid);


}
