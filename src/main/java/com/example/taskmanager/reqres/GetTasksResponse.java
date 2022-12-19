package com.example.taskmanager.reqres;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
@Api(value = "Вывод списка задач")
public class GetTasksResponse {
    @ApiModelProperty("Список задач без идентификатора")
    private final List<GetTaskResponse> getTasksList;

    public GetTasksResponse(List<GetTaskResponse> taskList) {
        getTasksList = new ArrayList<>(taskList);
    }

    @JsonProperty("taskList")
    public List<GetTaskResponse> setShowTaskLists() {
        return getTasksList;
    }
}
