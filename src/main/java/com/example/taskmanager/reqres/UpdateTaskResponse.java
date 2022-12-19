package com.example.taskmanager.reqres;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

@Api(value = "Результат обновления задачи")
public class UpdateTaskResponse {
    @ApiModelProperty(value = "Идентификатор обновленной задачи",required = true)
    private final Long id;

    public UpdateTaskResponse(Long id) {
        this.id = id;
    }

    @JsonProperty("id")
    public Long setId() {
        return id;
    }
}