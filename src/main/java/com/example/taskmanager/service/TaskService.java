package com.example.taskmanager.service;

import com.example.taskmanager.entity.Task;
import org.springframework.stereotype.Service;

@Service
public interface TaskService {
    Task createTask(String title, boolean isCompleted);
}
