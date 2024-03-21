package com.usercrud.usercrud.SECURITY.controller;

import com.usercrud.usercrud.User.Model.User;
import com.usercrud.usercrud.User.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/teste")
public class TesteController {

    private final UserRepository userRepository;

    // status da requisição : 401 - não está autenticado,
    //                        403 - não tem acesso aquela funcionalidade, permissão abaixo do seu nivel de usuário.

    @GetMapping
    public String teste(){

        Authentication auth =
                SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getPrincipal());

        return "Teste" + auth.getName() + "!";
    }

    @PostMapping
    public User registerUser(@RequestBody User user){
        return userRepository.save(user);
    }

    @GetMapping("/users")
    public Page<User> users(){
        Pageable pageable = PageRequest.of(
                0,20, Sort.Direction.ASC, "name"
        );
        return userRepository.findAll(pageable);
    }

    @GetMapping("/users2")
    public Page<User> users2(Pageable pageable){
        return userRepository.findAll(pageable);
    }

}
