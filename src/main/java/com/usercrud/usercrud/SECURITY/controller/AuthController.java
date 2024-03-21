package com.usercrud.usercrud.SECURITY.controller;

import com.usercrud.usercrud.SECURITY.utils.CookieUtil;
import com.usercrud.usercrud.SECURITY.utils.JwtUtil;
import com.usercrud.usercrud.SECURITY.model.dto.UserLoginDTO;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor

public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil = new JwtUtil();
    private final CookieUtil cookieUtil = new CookieUtil();

    @PostMapping("/auth/login")
    public ResponseEntity<String> auth(
            @RequestBody UserLoginDTO userLoginDTO,
            HttpServletRequest request,
            HttpServletResponse response){

        System.out.println("AAAAAAAAAAAA");

        try {
            Authentication authenticationToken =
                    new UsernamePasswordAuthenticationToken(userLoginDTO.getUsername(),userLoginDTO.getPassword());

            Authentication authentication = authenticationManager.authenticate(authenticationToken);

//            SecurityContext context = SecurityContextHolder.createEmptyContext();
//            context.setAuthentication(authentication);
//            SecurityContextHolder.setContext(context);
//            securityContextRepository.saveContext(context,request,response);

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Cookie cookie = cookieUtil.generateCookieJwt(userDetails);

            System.out.println(cookie);

            response.addCookie(cookie);

            return ResponseEntity.ok("Autenticação bem-sucedida!");

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falha na autenticação!");
        }
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request,
                       HttpServletResponse response){
        Cookie cookie = null;
        try {
            cookie = cookieUtil.getCookieFromWeb(request,"JWT");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(401);
        }
    }

}
