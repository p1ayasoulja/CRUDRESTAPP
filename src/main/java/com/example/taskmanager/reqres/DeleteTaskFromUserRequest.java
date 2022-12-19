package com.example.taskmanager.reqres;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
@Api(value = "Запрос на удаление задачи пользователя")
public class DeleteTaskFromUserRequest {
    @ApiModelProperty("Идентификатор пользователя")
    private final Long id;
    @JsonCreator
    public DeleteTaskFromUserRequest(@JsonProperty("id") Long id) {

        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
