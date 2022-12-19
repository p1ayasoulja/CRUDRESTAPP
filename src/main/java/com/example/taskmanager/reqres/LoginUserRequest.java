package com.example.taskmanager.reqres;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

@Api(value = "Запрос на авторизацию")
public class LoginUserRequest {
    @ApiModelProperty(value = "Имя пользователя")
    private final String username;
    @ApiModelProperty(value = "Пароль пользователя")
    private final String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @JsonCreator
    public LoginUserRequest(@JsonProperty("username") String username, @JsonProperty("password") String password) {
        this.username = username;
        this.password = password;
    }
}
