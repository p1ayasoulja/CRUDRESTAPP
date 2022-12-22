package com.example.taskmanager.service;

import com.example.taskmanager.entity.Task;

import java.util.List;
import java.util.Optional;


public interface TaskService {
    Task createTask(String title, boolean isCompleted);

    Task updateTask(Long id, String title, boolean completed);

    Optional<Task> get(Long id);
    List<Task> getTasksByTitle(String title);
    List<Task> getAllTasks();

    void deleteTaskById(Long id);
    Task changeTaskComplete(Long id);




}
