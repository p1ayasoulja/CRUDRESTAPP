package com.example.taskmanager.reqres;

import io.swagger.annotations.ApiModelProperty;

public class LoginUserResponse {
    @ApiModelProperty(value = "Имя пользователя")
    private String username;
    @ApiModelProperty(value = "Токен лога")
    private String token;

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }

    public LoginUserResponse(String username, String token) {
        this.username = username;
        this.token = token;
    }

    public LoginUserResponse(String username) {
        this.username = username;
    }
}
