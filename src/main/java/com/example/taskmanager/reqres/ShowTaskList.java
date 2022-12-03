package com.example.taskmanager.reqres;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShowTaskList {
    @ApiModelProperty("Список задач без идентификатора")
    private final List<ShowTaskResponse> showTaskLists;

    public ShowTaskList(List<ShowTaskResponse> taskList) {
        showTaskLists = new ArrayList<>(taskList);
        Collections.copy(showTaskLists, taskList);
    }

    @JsonProperty("taskList")
    public List<ShowTaskResponse> setShowTaskLists() {
        return showTaskLists;
    }
}
