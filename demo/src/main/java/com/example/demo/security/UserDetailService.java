package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private LoginService loginRepository;


    @Override
    public UserDetails loadUserByUsername(String idNumber) throws UsernameNotFoundException {
        String password = loginRepository.findPasswordByUserName(idNumber);
        return User.withUsername(idNumber)
                .password(password)
                .roles("USER").build();
    }
}