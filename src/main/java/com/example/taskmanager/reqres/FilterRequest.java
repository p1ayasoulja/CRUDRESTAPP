package com.example.taskmanager.reqres;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class FilterRequest {
    @ApiModelProperty("Описание задачи")
    String filter;

    public FilterRequest(@JsonProperty("title") String filter) {
        this.filter = filter;
    }

    public String getFilter() {
        return filter;
    }
}
