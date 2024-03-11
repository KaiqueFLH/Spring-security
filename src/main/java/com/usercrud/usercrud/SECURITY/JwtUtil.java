package com.usercrud.usercrud.SECURITY;

import io.jsonwebtoken.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

public class JwtUtil {

    public String generateToken(UserDetails userDetails){
        return Jwts.builder().issuer("Vertex")
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + 300000))
                .signWith(SignatureAlgorithm.NONE,"kaique222")
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
        return Jwts.parser().setSigningKey("kaique222").build();
    }

}
