package com.agnibank.agniBankAccount.service;

import com.agnibank.agniBankAccount.dto.ReqDto;
import com.agnibank.agniBankAccount.dto.RespDto;
import com.agnibank.agniBankAccount.model.Accounts;
import com.agnibank.agniBankAccount.repo.AccountRepo;
import com.agnibank.agniBankAccount.utill.JwtUtill;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Slf4j
@Service
public class AccountService {
    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private JwtUtill jwtUtill;

    public String fetchAllAccounts(String bearerToken) {
        try {
            log.info("token {}", bearerToken);
            String authToken = bearerToken.substring(7);
            Integer userId = getUsersDetails(authToken);
            return "all accounts are fetched";
        }catch (Exception e){
            e.printStackTrace();
        }
        return  "error";
    }



        private Integer getUsersDetails (String authToken){
            try {
                String authServiceUrl = "http://localhost:8080/auth/validate-token";
                String url = UriComponentsBuilder.fromUriString(authServiceUrl)
                        .queryParam("token", authToken)
                        .toUriString();
                Integer str = new RestTemplate().getForObject(url, Integer.class);
                return str;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

    public String generateRandom16DigitNumber() {
        // Generate a random 16-digit number
        long randomNumber = Math.abs(new java.util.Random().nextLong()) % 10000000000000000L;

        // Format the number to have exactly 16 digits
        String formattedNumber = String.format("%016d", randomNumber);

        return formattedNumber;
    }

    public ResponseEntity<RespDto> createAccount(String bearerToken , ReqDto obj) {
    try{
        String authToken = bearerToken.substring(7);
        Accounts acc = new Accounts();
        Integer userId = getUsersDetails(authToken);
        acc.setUserId(userId);
        acc.setAccountStatus(true);
        acc.setAccountType(obj.getAccountType());
        List<Accounts> allAcc = accountRepo.findByUserId(userId);
        System.out.println(allAcc);
        System.out.println(generateRandom16DigitNumber());
        String accontNumber = generateRandom16DigitNumber();
        acc.setAccountId(accontNumber);
        Accounts accounts = accountRepo.save(acc);
        RespDto resp = new RespDto();
        resp.setStatus(201);
        resp.setMessage("Accounts have been successfully created");
        return new ResponseEntity<>(resp, HttpStatus.CREATED);

    }catch (Exception e) {
        e.printStackTrace();
    }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
