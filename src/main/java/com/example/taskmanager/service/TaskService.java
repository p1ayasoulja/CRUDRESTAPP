package com.example.taskmanager.service;

import com.example.taskmanager.entity.Task;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TaskService {
    Task createTask(String title, boolean isCompleted);

    Task updateTask(Long id, String title, boolean completed);

    Task getTaskById(Long id);
    Task getTaskByTitle(String title);
    List<Task> getAllTasks();

    void deleteTaskById(Long id);

}
