package com.agnibank.agniBankAuth.DTO;

public class LoginDto extends  RespDto{

    private String token;
    private Boolean isLoggedIn;

    public LoginDto(Integer status, String message, String token, Boolean isLoggedIn) {
        super(status, message);
        this.token = token;
        this.isLoggedIn = isLoggedIn;
    }

    public LoginDto(String token, Boolean isLoggedIn) {
        this.token = token;
        this.isLoggedIn = isLoggedIn;
    }

    public Boolean getLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public LoginDto() {

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "LoginDto{" +
                "token='" + token + '\'' +
                ", isLoggedIn=" + isLoggedIn +
                '}';
    }
}
