package com.usercrud.usercrud.SECURITY;

import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.util.WebUtils;

import javax.crypto.SecretKey;
import java.beans.Encoder;
import java.security.Key;

@AllArgsConstructor
public class CookieUtil {



    public Cookie generateCookieJwt(UserDetails userDetails){
        String token = new JwtUtil().generateToken(userDetails);
        System.out.println(token);
        Cookie cookie = new Cookie("JWT", token);
        cookie.setPath("/");
        cookie.setMaxAge(3000);
        return cookie;
    }

    public Cookie getCookieFromWeb(HttpServletRequest request,
                                   String name){
        return WebUtils.getCookie(request,name);
    }

}
