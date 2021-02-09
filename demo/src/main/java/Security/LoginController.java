package Security;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class LoginController {
    @PostMapping("logIn")
    public String logIn(@RequestBody LoginCredentials loginCredentials) {
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
//            throw new BankException("Login failed!");
//        }

    }

//    private boolean validate(LoginCredentials loginCredentials) {
//        return ;
//    }
}
