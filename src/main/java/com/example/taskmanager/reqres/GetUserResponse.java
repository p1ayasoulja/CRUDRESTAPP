package com.example.taskmanager.reqres;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class GetUserResponse {
    @ApiModelProperty("Имя пользователя")
    private String username;
    @ApiModelProperty("Пароль")
    private String password;

    public GetUserResponse(@JsonProperty("username") String username, @JsonProperty("password") String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
