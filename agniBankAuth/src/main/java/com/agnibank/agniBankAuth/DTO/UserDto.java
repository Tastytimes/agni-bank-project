package com.agnibank.agniBankAuth.DTO;

import org.springframework.http.HttpStatus;

public class UserDto extends  RespDto{

    private User data;

    public UserDto(Integer status, String message, User data) {
        super(status, message);
        this.data = data;
    }

    public UserDto(User data) {
        this.data = data;
    }

    public UserDto() {

    }

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "data=" + data +
                '}';
    }
}
