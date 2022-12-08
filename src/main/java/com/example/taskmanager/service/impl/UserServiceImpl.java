package com.example.taskmanager.service.impl;

import com.example.taskmanager.entity.Task;
import com.example.taskmanager.entity.User;
import com.example.taskmanager.repository.TaskRepo;
import com.example.taskmanager.repository.UserRepo;
import com.example.taskmanager.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final TaskRepo taskRepo;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, TaskRepo taskRepo, BCryptPasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.taskRepo = taskRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {
        List<Task> taskList = new ArrayList<>();
        taskList.add(taskRepo.getById(user.getId()));

        user.setUsername(user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setTasks(taskList, user.getId());

        User registeredUser = userRepo.save(user);
        log.info("IN register - user : {} successfully registered", registeredUser);
        return registeredUser;
    }

    @Override
    public List<User> getAll() {
        List<User> userList = userRepo.findAll();
        log.info("IN getAll - {} users found", userList.size());
        return userList;
    }

    @Override
    public User findByUsername(String username) {
        User userByUsername = userRepo.findUserByUsername(username);
        if (userByUsername == null) {
            log.info("IN findByUsername - user : {} was not found", username);
            return null;
        }
        log.info("IN findByUsername - user : {} found", username);
        return userByUsername;
    }

    @Override
    public User findById(Long id) {
        Optional<User> userOpt = userRepo.findById(id);
        if (userOpt.isEmpty()) {
            log.warn("IN findById - no use found by id : {}", id);
            return null;
        }
        log.info("IN findById - user : {} found", id);
        return userOpt.get();
    }

    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
        log.info("IN deleteUser - user : {} deleted", id);
    }

    public List<Task> addTask(List<Task> taskList, Task task) {
        if (!taskList.contains(task)) {
            taskList.add(task);
            log.info("IN addTaskToUser - task : {} added", task.getId());
            return taskRepo.saveAll(taskList);
        } else {
            log.info("IN addTaskToUser - task : {} already exists", task.getId());
            return null;
        }
    }

    public void deleteTask(List<Task> taskList, Task task) {
        if (taskList.contains(task)) {
            taskList.remove(task);
            taskRepo.saveAll(taskList);
            log.info("IN deleteTaskFromUser - task : {} deleted", task.getId());
        } else {
            log.info("IN deleteTaskFromUser - task : {} is not contained in User tasks list", task.getId());
        }
    }
}
