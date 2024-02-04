package com.agnibank.agniBankAuth.DTO;


import org.springframework.http.HttpStatus;

public class RespDto {

    private Integer status;
    private String message;

    public RespDto(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public RespDto() {

    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "RespDto{" +
                "status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
