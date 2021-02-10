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
    public LoginRepository loginRepository;

    @CrossOrigin
    @PostMapping("public/login")
    public String login(@RequestBody LoginCredentials loginCredentials) {
        if (validate(loginCredentials)) {
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

    public boolean validate(LoginCredentials loginCredentials) {
        String encodedPassword = loginRepository.findPasswordByIdNumber(loginCredentials.getIdNumber());
        return passwordEncoder.matches(loginCredentials.getPassword(), encodedPassword);
    }

//    public void savePassword(String password) {
//        String encodedPassword = passwordEncoder.encode(password);
//    }
}

