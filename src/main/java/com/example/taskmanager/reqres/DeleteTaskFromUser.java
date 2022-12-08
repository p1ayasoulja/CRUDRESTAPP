package com.example.taskmanager.reqres;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class DeleteTaskFromUser {
    @ApiModelProperty("Идентификатор пользователя")
    private Long id;
    @JsonCreator
    public DeleteTaskFromUser(@JsonProperty("id") Long id) {

        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
