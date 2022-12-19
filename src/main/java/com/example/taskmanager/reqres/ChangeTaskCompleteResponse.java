package com.example.taskmanager.reqres;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

@Api(value = "Запрос на изменение статуса задачи")
public class ChangeTaskCompleteResponse {
    @ApiModelProperty("Статус задачи")
    private final Boolean isCompleted;

    public ChangeTaskCompleteResponse(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }
}
