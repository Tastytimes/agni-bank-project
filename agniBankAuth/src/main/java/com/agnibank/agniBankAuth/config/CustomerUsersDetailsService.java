package com.agnibank.agniBankAuth.config;

import com.agnibank.agniBankAuth.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service
public class CustomerUsersDetailsService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;
    private  com.agnibank.agniBankAuth.model.Users userDetail;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        userDetail = userRepo.findByEmail(username);
        if(!Objects.isNull(userDetail))
            return new User(userDetail.getEmail(),userDetail.getPassword(), new ArrayList<>());
        else
            throw new UsernameNotFoundException("user not found");

    }

    public com.agnibank.agniBankAuth.model.Users getuserDetail() {
        return userDetail;
    }

}
