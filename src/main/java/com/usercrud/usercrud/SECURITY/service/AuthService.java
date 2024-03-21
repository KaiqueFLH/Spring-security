package com.usercrud.usercrud.SECURITY.service;

import com.usercrud.usercrud.User.Model.User;
import com.usercrud.usercrud.User.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUserDetailsENTITY_Username(username);

        System.out.println(userOptional);

        if (userOptional.isPresent()){
            return userOptional.get().getUserDetailsENTITY();
        }
        else throw new UsernameNotFoundException("Dados Inválidos!!");
    }
}
