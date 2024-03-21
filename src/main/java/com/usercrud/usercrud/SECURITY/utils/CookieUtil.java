package com.usercrud.usercrud.SECURITY.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.util.WebUtils;

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
                                   String name) throws Exception {
        Cookie cookie = WebUtils.getCookie(request,name);

        if (cookie!=null) return cookie;
        else throw new Exception("Cookie não encontrado!!");
    }

}
