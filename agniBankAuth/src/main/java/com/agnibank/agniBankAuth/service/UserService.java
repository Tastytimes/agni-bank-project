package com.agnibank.agniBankAuth.service;

import com.agnibank.agniBankAuth.DTO.*;
import com.agnibank.agniBankAuth.config.JwtFilter;
import com.agnibank.agniBankAuth.config.JwtUtill;
import com.agnibank.agniBankAuth.model.Users;
import com.agnibank.agniBankAuth.repo.UserRepo;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class UserService {
    @Autowired
   private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtill jwtUtill;

    @Autowired
    private JwtFilter jwtFilter;

    Claims claims = null;
    private String userName = null;

    public ResponseEntity<UserDto> saveUser(Users obj) {
       try {
           Users reqBody = new Users();
           reqBody.setName(obj.getName());
           reqBody.setEmail(obj.getEmail());
           reqBody.setPassword(passwordEncoder.encode(obj.getPassword()));
            Users user = userRepo.save(reqBody);
            User userDto = new User();
            userDto.setName(user.getName());
            userDto.setEmail(user.getEmail());
           UserDto dto = new UserDto();
           dto.setStatus(201);
           dto.setMessage("User Created Successfully");
           dto.setData(userDto);
           log.info("return Object {}", dto);
            return new ResponseEntity<UserDto>(dto, HttpStatus.CREATED);
       }catch (Exception e) {
           e.printStackTrace();
       }

        return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }


    public ResponseEntity<LoginDto> login(Map<String, String> requestMap) {
        try{
            Users userDetails = userRepo.findByEmail(requestMap.get("email"));
            Authentication auth =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestMap.get("email"), requestMap.get("password")));
            log.info("auth {}", auth);
            LoginDto dto = new LoginDto();
            if(auth.isAuthenticated()){
                String genegratedToken = jwtUtill.generateToken(requestMap.get("email"), userDetails.getId());
                dto.setStatus(200);
                dto.setMessage("Logged in Successfully");
                dto.setToken(genegratedToken);
                dto.setLoggedIn(true);
                return  new ResponseEntity<>(dto, HttpStatus.OK);
            }else {
                dto.setStatus(404);
                dto.setMessage("Email and Password does not match");
                dto.setToken("");
                dto.setLoggedIn(false);
                return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<UserDto> getProfileDetails() {
        UserDto dto = null;
        try {
            String name = jwtFilter.getCurrentUser();
            Users optionalUsers = userRepo.findByEmail(name);
            Users users = optionalUsers;
            dto = new UserDto();
            User user = new User();
            if (Objects.isNull(users)) {
                dto.setStatus(404);
                dto.setMessage("Invalid Token, Please log out and re login");
                user.setName("");
                user.setEmail("");
                dto.setData(user);
                return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
            } else {
                dto.setStatus(200);
                dto.setMessage("profile data fetched succesfully");
                user.setName(users.getName());
                user.setEmail(users.getEmail());
                dto.setData(user);
                return new ResponseEntity<>(dto, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("dto {}", dto);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<UserDto> updateProfile(User obj) {
        try {
            String email = jwtFilter.getCurrentUser();
            Optional<Users> optionalUsers = Optional.ofNullable(userRepo.findByEmail(email));
            log.info("optional Users {}", optionalUsers);
            Users users = optionalUsers.orElse(null);
            log.info(" Users {}", users);
            User user = new User();
            if (users != null) {
                users.setName(obj.getName());
                users.setEmail(obj.getEmail());
                Users respDto = userRepo.save(users);
                UserDto dto = new UserDto();
                dto.setStatus(200);
                dto.setMessage("Profile updated Successfully");
                user.setName(users.getName());
                user.setEmail(users.getEmail());
                dto.setData(user);
                return new ResponseEntity<>(dto, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<RespDto> changePassword(PasswordReqDto obj) {
        try {
            String email = jwtFilter.getCurrentUser();
            Users users = userRepo.findByEmail(email);
            RespDto dto = new RespDto();
            if (!users.equals(null)) {
                if (new BCryptPasswordEncoder().matches(obj.getOldPassword(), users.getPassword())) {
                    users.setPassword(new BCryptPasswordEncoder().encode(obj.getNewPassword()));
                    userRepo.save(users);
                    dto.setStatus(200);
                    dto.setMessage("Password has been changed Succcessfully");
                    return new ResponseEntity<>(dto, HttpStatus.OK);
                } else {
                    dto.setStatus(400);
                    dto.setMessage("Password didn't match");
                    return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<Integer> validateToken(String token) {
        Claims cl = jwtUtill.getProfileDetails(token);
        Integer userId = cl.get("id", Integer.class);
        return new ResponseEntity<Integer>(userId,  HttpStatus.OK);
    }

}
