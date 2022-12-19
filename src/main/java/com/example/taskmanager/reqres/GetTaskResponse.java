package com.example.taskmanager.reqres;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
@Api(value = "Вывод одной задачи")
public class GetTaskResponse {

    @ApiModelProperty(value = "Статус выполнения таска", required = true)
    private final boolean isCompleted;
    @ApiModelProperty(value = "Описание таска", required = true)
    private final String title;

    @JsonCreator
    public GetTaskResponse(@JsonProperty("isCompleted") boolean isCompleted, @JsonProperty("title") String title) {
        this.isCompleted = isCompleted;
        this.title = title;
    }
    @JsonProperty("isCompleted")
    public boolean isCompleted() {
        return isCompleted;
    }
    @JsonProperty("title")
    public String getTitle() {
        return title;
    }
}
