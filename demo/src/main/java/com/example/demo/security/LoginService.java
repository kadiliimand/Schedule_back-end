package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LoginService {
@Autowired
private LoginRepository loginRepository;

    public String findPasswordByUserName(String idNumber) {
        return loginRepository.findPasswordByIdNumber(idNumber);
    }

}
