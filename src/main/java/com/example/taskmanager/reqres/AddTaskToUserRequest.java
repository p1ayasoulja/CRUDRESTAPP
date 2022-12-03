package com.example.taskmanager.reqres;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class AddTaskToUserRequest {
    @ApiModelProperty("Идентификатор пользователя")
    private Long id;
    @JsonCreator
    public AddTaskToUserRequest(@JsonProperty("id") Long id) {

        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
