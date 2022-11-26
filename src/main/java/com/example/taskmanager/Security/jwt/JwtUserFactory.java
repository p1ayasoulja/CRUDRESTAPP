package com.example.taskmanager.Security.jwt;

import com.example.taskmanager.entity.Task;
import com.example.taskmanager.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {
    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(user.getId(),
                user.getUsername(),
                user.getPassword(),
                mapToGrantedAuthority(new ArrayList<>(user.getTasks())),
                true);
    }

    private static List<GrantedAuthority> mapToGrantedAuthority(List<Task> userTasks) {
        return userTasks.stream()
                .map(task ->
                        new SimpleGrantedAuthority(task.getTitle())).collect(Collectors.toList());


    }
}
