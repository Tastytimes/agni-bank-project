package com.agnibank.agniBankAuth.controller;

import com.agnibank.agniBankAuth.DTO.*;
import com.agnibank.agniBankAuth.model.Users;
import com.agnibank.agniBankAuth.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserService userService;
    @GetMapping("/get-users")
    public String getAllUsers(){
        return "fetched all users";
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> saveUser(@Valid @RequestBody Users obj) {
        return userService.saveUser(obj);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginDto> login(@RequestBody(required=true) Map<String, String> requestMap){
        return userService.login(requestMap);
    }

    @GetMapping("/profile")
    public ResponseEntity<UserDto> getProfile() {
        return userService.getProfileDetails();
    }

    @PutMapping("/update-profile")
    public ResponseEntity<UserDto> updateProfile(@RequestBody User obj){
        return userService.updateProfile(obj);
    }

    @PostMapping("/change-password")
    public ResponseEntity<RespDto> changePassword(@RequestBody PasswordReqDto obj){
        return userService.changePassword(obj);
    }

//    @GetMapping("/validate-token")
//    public ResponseEntity<String> validateToken(@RequestHeader("Authorization") String bearerToken){
//        return userService.validateToken(bearerToken);
//    }

    @GetMapping("/validate-token")
    public ResponseEntity<Integer> validateToken(@RequestParam String token){
        return userService.validateToken(token);
    }




}
