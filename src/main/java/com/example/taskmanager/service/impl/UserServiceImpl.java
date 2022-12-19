package com.example.taskmanager.service.impl;

import com.example.taskmanager.Security.jwt.JwtUser;
import com.example.taskmanager.Security.jwt.JwtUserFactory;
import com.example.taskmanager.entity.Role;
import com.example.taskmanager.entity.Task;
import com.example.taskmanager.entity.User;
import com.example.taskmanager.repository.RoleRepo;
import com.example.taskmanager.repository.TaskRepo;
import com.example.taskmanager.repository.UserRepo;
import com.example.taskmanager.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepo userRepo;
    private final TaskRepo taskRepo;
    private final RoleRepo roleRepo;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, TaskRepo taskRepo, RoleRepo roleRepo, BCryptPasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.taskRepo = taskRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String username, String password) {
        Role roleUser = roleRepo.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(userRoles);
        User registeredUser = userRepo.save(user);

        log.info("IN register - user: {} successfully registered", registeredUser);

        return registeredUser;
    }


    @Override
    public List<User> getAll() {
        List<User> userList = userRepo.findAll();
        log.info("IN getAll - {} users found", userList.size());
        return userList;
    }

    @Override
    public Optional<User> findByUsername(String username) {
       Optional<User> userOpt = userRepo.findUserByUsername(username);
        if (userOpt.isEmpty()) {
            log.info("IN findByUsername - user : {} was not found", username);
            return userOpt;
        }
        log.info("IN findByUsername - user : {} found", username);
        return userOpt;
    }

    @Override
    public Optional<User> findById(Long id) {
        Optional<User> userOpt = userRepo.findById(id);
        log.info("IN findById - user : {} found", userOpt);
        return userOpt;
    }

    public Task addTask(Long userid, Long taskid) {
        User user=userRepo.getById(userid);
        Task task=taskRepo.getById(taskid);
        if (!user.getTasks().contains(task)) {
            task.setOwner(user);
            log.info("IN addTaskToUser - task : {} added", task.getId());
            return taskRepo.save(task);
        } else {
            log.info("IN addTaskToUser - task : {} already exists", task.getId());
            return task;
        }
    }


    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
        log.info("IN deleteUser - user : {} deleted", id);
    }

    public void deleteTask(Long userid, Long taskid) {
        User user=userRepo.getById(userid);
        Task task=taskRepo.getById(taskid);
        if (user.getTasks().contains(task)) {
            task.setOwner(null);
            taskRepo.save(task);
            log.info("IN deleteTaskFromUser - task : {} deleted from User", task.getId());
        } else {
            log.info("IN deleteTaskFromUser - task : {} is not contained in User tasks list", task.getId());
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Optional<User> userOpt = findByUsername(username);
        if (userOpt.isEmpty()) {
            throw new UsernameNotFoundException("Cant find user with username :" + username);
        }
        JwtUser jwtUser = JwtUserFactory.create(userOpt.get());
        log.info("IN loadByUsername - user with: {} loaded", username);
        return jwtUser;
    }
}
