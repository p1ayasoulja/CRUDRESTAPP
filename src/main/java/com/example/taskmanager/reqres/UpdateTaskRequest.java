package com.example.taskmanager.reqres;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

@Api(value = "Запрос на обновление задачи")

public class UpdateTaskRequest {
    @ApiModelProperty(value = "Статус выполнения таска", required = true)
    private final boolean isCompleted;
    @ApiModelProperty(value = "Описание таска", required = true)
    private final String title;
    @JsonCreator
    public UpdateTaskRequest(@JsonProperty("id") Long id,@JsonProperty("isCompleted") boolean isCompleted, @JsonProperty("title") String title) {
        this.isCompleted = isCompleted;
        this.title = title;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public String getTitle() {
        return title;
    }
}