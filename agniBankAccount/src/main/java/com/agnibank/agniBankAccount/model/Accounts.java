package com.agnibank.agniBankAccount.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Accounts {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;


    private String accountId;

    private String accountType;
    private Integer userId;
    private Boolean accountStatus;

    public Accounts(Integer id, String accountId, String accountType, Integer userId, Boolean accountStatus) {
        this.id = id;
        this.accountId = accountId;
        this.accountType = accountType;
        this.userId = userId;
        this.accountStatus = accountStatus;
    }

    public Accounts(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Boolean getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(Boolean accountStatus) {
        this.accountStatus = accountStatus;
    }

    @Override
    public String toString() {
        return "Accounts{" +
                "id=" + id +
                ", accountId=" + accountId +
                ", accountType='" + accountType + '\'' +
                ", userId=" + userId +
                ", accountStatus=" + accountStatus +
                '}';
    }
}
