package com.usercrud.usercrud.SECURITY;

import com.usercrud.usercrud.User.Model.DTO.UserLoginDTO;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil = new JwtUtil();
    private final CookieUtil cookieUtil = new CookieUtil();

    @PostMapping("/login")
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

}
