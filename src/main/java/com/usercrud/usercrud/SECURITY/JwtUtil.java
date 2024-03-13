package com.usercrud.usercrud.SECURITY;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    public String generateToken(UserDetails userDetails) {
        Algorithm algorithm = Algorithm.HMAC256("kaique");
        return JWT.create().withIssuer("Vertex")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(new Date().getTime() + 300000))
                .withSubject(userDetails.getUsername())
                .sign(algorithm);
    }

    public String getUsername(String token) {
        return JWT.decode(token).getSubject();
    }

}
