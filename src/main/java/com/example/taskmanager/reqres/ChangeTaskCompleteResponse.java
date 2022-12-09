package com.example.taskmanager.reqres;

import io.swagger.annotations.ApiModelProperty;

public class ChangeTaskCompleteResponse {
    @ApiModelProperty("Статус выполнения задачи")
    private final Boolean isCompleted;

    public ChangeTaskCompleteResponse(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }
}
