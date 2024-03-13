package com.usercrud.usercrud.SECURITY;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.boot.autoconfigure.pulsar.PulsarProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class AuthFilter extends OncePerRequestFilter {

    private final CookieUtil cookieUtil = new CookieUtil();
    private final JwtUtil jwtUtil = new JwtUtil();
    private final SecurityContextRepository securityContextRepository;
    private final AuthService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        if (!isPublicRoute(request)){



            // Busca e Validação do Token.
            Cookie cookie = null;
            try {
                cookie = cookieUtil.getCookieFromWeb(request, "JWT");
            } catch (Exception e) {
                e.printStackTrace();
                response.setStatus(401);
                return;
            }

            //Valida o JWT.
            String token = cookie.getValue();
            String username = jwtUtil.getUsername(token);

            // Criação do usuário autenticado.
            UserDetails userDetails = authService.loadUserByUsername(username);
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    userDetails.getPassword(),
                    userDetails.getAuthorities());

            // Salvamento do usuário autenticado no SecurityContext.
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authentication);
            securityContextRepository.saveContext(context, request, response);

            // Renovação do JWT e Cookie
            Cookie cookieRenovado = cookieUtil.generateCookieJwt(userDetails);
            response.addCookie(cookieRenovado);
        }

        // Dando continuidade na requisição.
        filterChain.doFilter(request, response);

    }

    private boolean isPublicRoute(HttpServletRequest request){
        return request.getRequestURI().equals("/auth/login") &&
                (request.getMethod().equals("POST"));
    }

}
