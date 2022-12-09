package com.example.taskmanager.service.impl;

import com.example.taskmanager.entity.Task;
import com.example.taskmanager.repository.TaskRepo;
import com.example.taskmanager.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TaskServiceImpl implements TaskService {
    private final TaskRepo taskRepo;

    @Autowired
    public TaskServiceImpl(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }


    @Override
    public Task createTask(String title, boolean isCompleted) {
        Task task = taskCreator(title, isCompleted);
        return taskRepo.save(task);
    }

    @Override
    public Task updateTask(Long id, String title, boolean completed) {

        Task task = taskCreator(title, completed);
        task.setId(id);
        return taskRepo.save(task);
    }

    private Task taskCreator(String title, boolean isCompleted) {
        Task task = new Task();
        task.setCompleted(isCompleted);
        task.setTitle(title);
        task.setCreationDate(LocalDate.now());
        return task;

    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepo.findById(id).get();
    }

    @Override
    public void deleteTaskById(Long id) {
        if(taskRepo.findAll().contains(taskRepo.getById(id))){
            taskRepo.deleteById(id);
            log.info("IN deleteTaskFromUser - task : {} deleted from User", id);
        }
        else log.info("IN deleteTaskFromUser - task : {} is not exist", id);
    }


    @Override
    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    public Task getTaskByTitle(String title) {
        return taskRepo.findByTitle(title);
    }

    public Task changeTaskComplete(Long id) {
        Task task = taskRepo.getById(id);
        task.setCompleted(!task.isCompleted());
        return taskRepo.save(task);
    }
}
