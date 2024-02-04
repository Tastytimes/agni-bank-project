package com.agnibank.agniBankAccount.controller;

import com.agnibank.agniBankAccount.dto.ReqDto;
import com.agnibank.agniBankAccount.dto.RespDto;
import com.agnibank.agniBankAccount.model.Accounts;
import com.agnibank.agniBankAccount.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private RestTemplate template;

    @Autowired
    private AccountService accountService;
    @GetMapping("/get-accounts")
    public String getAllAccounts(@RequestHeader("Authorization") String bearerToken) {
        return accountService.fetchAllAccounts(bearerToken);
    }

    @PostMapping("/create-account")
    public ResponseEntity<RespDto> createAccount(@RequestHeader("Authorization") String bearerToken, @RequestBody ReqDto obj){
        return accountService.createAccount(bearerToken,obj);
    }
}
