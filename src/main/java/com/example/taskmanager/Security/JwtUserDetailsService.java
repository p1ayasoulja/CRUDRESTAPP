package com.example.taskmanager.Security;

import com.example.taskmanager.Security.jwt.JwtUser;
import com.example.taskmanager.Security.jwt.JwtUserFactory;
import com.example.taskmanager.entity.User;
import com.example.taskmanager.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    private  UserService userService;

    @Autowired
    public void setJwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException("Cant find user with username :" + username);
        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("IN loadByUsername - user with: {} loaded", username);
        return jwtUser;
    }

}
