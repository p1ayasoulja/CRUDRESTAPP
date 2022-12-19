package com.example.taskmanager.reqres;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
@Api(value = "Запрос на поиск задачи по описанию")
public class FilterTaskByTitleRequest {
    @ApiModelProperty("Описание задачи")
    String title;

    public FilterTaskByTitleRequest(@JsonProperty("title") String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
