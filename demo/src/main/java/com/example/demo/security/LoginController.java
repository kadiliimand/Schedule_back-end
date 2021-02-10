package com.example.demo.security;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
//@Autowired
//private PasswordEncoder passwordEncoder;
@RestController
public class LoginController {
    @GetMapping("logIn")
    public String logIn(@RequestBody LoginCredentials loginCredentials) throws ScheduleException {
//        if (validate(loginCredentials)) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + 1000 * 60 * 60);
        JwtBuilder builder = Jwts.builder()
                .setExpiration(expiration)
                .setIssuedAt(new Date())
                .setIssuer("ScheduleViewer")
                .signWith(SignatureAlgorithm.HS256, "test123")
                .claim("inNumber", loginCredentials.getIdNumber());

        return builder.compact();
//        } else {
//            throw new ScheduleException("Login failed!");
//        }

    }

//    private boolean validate(LoginCredentials loginCredentials) {
//        String encodedPassword = scheduleRepository.requestPassword(loginCredentials.getRawPassword);
//        return passwordEncoder.matches(loginCredentials.getRawPassword(), encodedPassword);
//    }
}
