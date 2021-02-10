package com.example.demo.security;

import com.example.demo.errorHandling.ScheduleException;
import com.example.demo.repositories.ScheduleRepository;
import com.example.demo.security.LoginCredentials;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class LoginController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    public LoginRepository loginRepository;

    @CrossOrigin
    @PostMapping("public/login")
    public String login(String idNumber, String password) {
        if (validate(idNumber, password)) {
            Date now = new Date();
            Date expiration = new Date(now.getTime() + 1000 * 60 * 60);
            JwtBuilder builder = Jwts.builder()
                    .setExpiration(expiration)
                    .setIssuedAt(new Date())
                    .setIssuer("scheduleViewer")
                    .signWith(SignatureAlgorithm.HS256, "test123")
                    .claim("idNumber", "user");

            return builder.compact();
        } else {
            throw new ScheduleException("Login failed!");
        }

    }

    public boolean validate(String idNumber, String password) {
        String encodedPassword = loginRepository.findPasswordByIdNumber(idNumber);
        return passwordEncoder.matches(password, encodedPassword);
    }

//    public void savePassword(String password) {
//        String encodedPassword = passwordEncoder.encode(password);
//    }
}

