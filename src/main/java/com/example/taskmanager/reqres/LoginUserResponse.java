package com.example.taskmanager.reqres;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
@Api(value = "Ответ на авторизацию")
public class LoginUserResponse {
    @ApiModelProperty(value = "Токен лога")
    private  final String token;

    public String getToken() {
        return token;
    }

    public LoginUserResponse(String token) {
        this.token = token;
    }


}
