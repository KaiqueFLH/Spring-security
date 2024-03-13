package com.usercrud.usercrud.SECURITY;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextRepository;

@Configuration
@AllArgsConstructor
public class SecurityConfig {
    private final AuthFilter authFilter;
    private final SecurityContextRepository repo;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                        .requestMatchers(HttpMethod.GET, "/teste").hasAuthority("GET")
                        .requestMatchers(HttpMethod.GET, "/teste/users").permitAll()
                        .requestMatchers(HttpMethod.GET, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/logout").authenticated()
                        .anyRequest().authenticated());

        httpSecurity.securityContext((context) -> {
            context.securityContextRepository(repo);
        });
//        httpSecurity.formLogin(configurer->{
//            configurer.disable();
//        });
        httpSecurity.formLogin(AbstractHttpConfigurer::disable);
        httpSecurity.logout(AbstractHttpConfigurer::disable);

        httpSecurity.sessionManagement(configurer ->{
            configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS);});
        httpSecurity.addFilterBefore(authFilter,UsernamePasswordAuthenticationFilter.class);

//      httpSecurity.httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }

    //exemplo para armazenar usuarios em memória.
//    @Bean
//    public UserDetailsManager inMemoryUserDetailsManager(){
//        UserDetails user1 = User.withDefaultPasswordEncoder().username("mi72").password("M!7dois").build();
//        UserDetails user2 = User.withDefaultPasswordEncoder().username("mi72").password("M!7dois").build();
//        return new InMemoryUserDetailsManager(user1,user2);
//    }

}
