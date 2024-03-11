package com.usercrud.usercrud.User.Controller;

import com.usercrud.usercrud.Archive;
import com.usercrud.usercrud.User.Model.DTO.UserCreateDTO;
import com.usercrud.usercrud.User.Model.DTO.UserStatusDTO;
import com.usercrud.usercrud.User.Model.DTO.UserPasswordDTO;
import com.usercrud.usercrud.User.Model.User;
import com.usercrud.usercrud.User.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping()
    public ResponseEntity<User> post(@RequestBody UserCreateDTO user){
        try{
            return new ResponseEntity<>(this.userService.save(user),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/cadastrar-comFoto")
    public ResponseEntity<User> post(@RequestParam String user, @RequestParam MultipartFile archive){
        try {
            this.userService.save(user,archive);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping()
    public User put(@RequestBody UserCreateDTO user){
        return this.userService.save(user);
    }

    @DeleteMapping("/{idUser}")
    public void deleteUser(@PathVariable Long idUser){
        this.userService.delete(idUser);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        return this.userService.findById(id);
    }

    @GetMapping
    public List<User> getUsers(){
        return this.userService.findAllUsers();
    }


    @PatchMapping("/password")
    public ResponseEntity<User> patchPassword(@RequestBody UserPasswordDTO userPasswordDTO){

        try {
            return new ResponseEntity<>(userService.patchPassword(userPasswordDTO),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PatchMapping("/status")
    public ResponseEntity<User> patchStatus(@RequestBody UserStatusDTO userStatusDTO){

        try {
            return new ResponseEntity<>(userService.patchStatus(userStatusDTO),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PatchMapping("/photo/{id}")
    public ResponseEntity<User> patchPhoto(@PathVariable Long id, @RequestParam MultipartFile multipartFile){
        try {
            userService.patchFile(id,multipartFile);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

}
