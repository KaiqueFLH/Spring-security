package com.usercrud.usercrud.SECURITY.configs;

import com.usercrud.usercrud.SECURITY.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@AllArgsConstructor
public class BeanConfig {

    private final AuthService authService;


    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
        dao.setPasswordEncoder(new BCryptPasswordEncoder());
        dao.setUserDetailsService(authService);
        return new ProviderManager(dao);
    }
//    @Autowired
//    public void configGlobal(AuthenticationManagerBuilder auth,AuthService authService) throws Exception {
//        auth.userDetailsService(authService).passwordEncoder(NoOpPasswordEncoder.getInstance());
//    }

    @Bean
    public CorsConfigurationSource corsConfig(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:4200"));
        corsConfiguration.setAllowedMethods(List.of("*"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",corsConfiguration);
        return source;
    }

    @Bean
    public SecurityContextRepository securityContextRepository() {
        return new HttpSessionSecurityContextRepository();
    }


//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        return authService;
//    }

//    @Autowired
//    public void config(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(authService).passwordEncoder(NoOpPasswordEncoder.getInstance());
//    }


    //exemplo para armazenar usuarios em memória.
//    @Bean
//    public UserDetailsManager inMemoryUserDetailsManager(){
//        UserDetails user1 = User.withDefaultPasswordEncoder().username("mi72").password("M!7dois").build();
//        UserDetails user2 = User.withDefaultPasswordEncoder().username("mi72").password("M!7dois").build();
//        return new InMemoryUserDetailsManager(user1,user2);
//    }

}
