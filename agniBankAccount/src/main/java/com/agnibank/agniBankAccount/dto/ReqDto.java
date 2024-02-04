package com.agnibank.agniBankAccount.dto;

public class ReqDto {

    private String accountType;


    public ReqDto(String accountType) {
        this.accountType = accountType;

    }

    public ReqDto() {

    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }


    @Override
    public String toString() {
        return "ReqDto{" +
                "accountType='" + accountType + '\'' +
                '}';
    }
}
