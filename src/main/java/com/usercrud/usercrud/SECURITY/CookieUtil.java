package com.usercrud.usercrud.SECURITY;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.util.WebUtils;

public class CookieUtil {

    public Cookie generateCookieJwt(UserDetails userDetails){
        String token = new JwtUtil().generateToken(userDetails);
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
