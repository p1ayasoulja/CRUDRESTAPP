package com.example.taskmanager.reqres;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
@Api(value = "Ответ на регистрацию пользователся")
public class RegisterUserResponse {
    @ApiModelProperty("Идентификатор пользователя")
    private Long id;

    public RegisterUserResponse(@JsonProperty("id") Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setUsername(Long id) {
        this.id = id;

    }
}
