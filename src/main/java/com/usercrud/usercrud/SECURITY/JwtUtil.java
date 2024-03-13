package com.usercrud.usercrud.SECURITY;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    private final SecretKey secretKey;

    public JwtUtil(){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String senha = passwordEncoder.encode("kaique");
        this.secretKey = Keys.hmacShaKeyFor(senha.getBytes());
    }

    public String generateToken(UserDetails userDetails){
        return Jwts.builder().issuer("Vertex")
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + 300000))
                .signWith(this.secretKey,Jwts.SIG.HS512)
                .subject(userDetails.getUsername())
                .compact();
    }

    public Jws<Claims> validateToken(String token){
        return getParser().parseSignedClaims(token);
    }

    public String getUsername(String token){
        return validateToken(token).getPayload().getSubject();
    }

    public JwtParser getParser(){
        return Jwts.parser().verifyWith(secretKey).build();
    }

}
