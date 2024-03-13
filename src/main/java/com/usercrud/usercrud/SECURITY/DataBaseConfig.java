package com.usercrud.usercrud.SECURITY;

import com.usercrud.usercrud.User.Model.User;
import com.usercrud.usercrud.User.Repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@AllArgsConstructor
@Configuration
public class DataBaseConfig {

    private final UserRepository userRepository;

    @PostConstruct
    public void init() {
        User user = new User();
        user.setName("TESTE");
        user.setStatus(true);
        user.setAge(18);
        user.setUserDetailsENTITY(
                UserDetailsENTITY.builder()
                        .user(user)
                        .enabled(true)
                        .accountNonExpired(true)
                        .accountNonLocked(true).credentialsNonExpired(true)
                        .username("kaique")
                        .password(new BCryptPasswordEncoder().encode("kaique"))
                        .authorities(List.of(Authorities.GET))
                        .build());
        userRepository.save(user);
    }

}
