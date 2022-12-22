package com.example.taskmanager.service.impl;

import com.example.taskmanager.entity.Task;
import com.example.taskmanager.repository.TaskRepo;
import com.example.taskmanager.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
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
        Task task = create(title, isCompleted);
        return taskRepo.save(task);
    }

    @Override
    public Task updateTask(Long id, String title, boolean completed) {

        Task task = create(title, completed);
        task.setId(id);
        return taskRepo.save(task);
    }

    private Task create(String title, boolean isCompleted) {
        Task task = new Task();
        task.setCompleted(isCompleted);
        task.setTitle(title);
        task.setCreationDate(LocalDate.now());
        return task;

    }

    @Override
    public Optional<Task> get(Long id) {
        if (taskRepo.existsById(id)) {
            return taskRepo.findById(id);
        } else {
            log.info("IN get - task : {} is not exist", id);
            return Optional.empty();
        }
    }

    @Override
    public void deleteTaskById(Long id) {
        if (taskRepo.existsById(id)) {
            taskRepo.deleteById(id);
            log.info("IN deleteTaskFromUser - task : {} deleted from User", id);
        } else log.info("IN deleteTaskFromUser - task : {} is not exist", id);
    }


    @Override
    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    public List<Task> getTasksByTitle( String title) {
        List<Task> taskListRepo = taskRepo.findAll();
        List<Task> taskList = new ArrayList<>();
        if (!title.isEmpty()) {
            taskListRepo.forEach(task -> {
                        if (task.getTitle().equals(title))
                            taskList.add(task);
                    }
            );
            return taskList;
        } else {
            return taskListRepo;
        }
    }

    public Task changeTaskComplete(Long id) {
        Task task = taskRepo.getById(id);
        task.setCompleted(!task.isCompleted());
        return taskRepo.save(task);
    }
}
