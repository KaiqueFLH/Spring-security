package com.usercrud.usercrud.User.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usercrud.usercrud.Archive;
import com.usercrud.usercrud.User.Model.DTO.UserCreateDTO;
import com.usercrud.usercrud.User.Model.DTO.UserStatusDTO;
import com.usercrud.usercrud.User.Model.DTO.UserPasswordDTO;
import com.usercrud.usercrud.User.Model.User;
import com.usercrud.usercrud.User.Repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
@Data
public class UserService {

    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;

    public User save(UserCreateDTO userCreateDTO) {
        User user = new User();

        BeanUtils.copyProperties(userCreateDTO,user);

        return this.userRepository.save(user);
    }

    public void save(String user, MultipartFile multipartFile) throws IOException {
        UserCreateDTO userCreateDTO = objectMapper.readValue(user,UserCreateDTO.class);
        userCreateDTO.setPhoto(multipartFile);
        save(userCreateDTO);
    }

    public User patchPassword(UserPasswordDTO userPasswordDTO){
        User userCopy = new User();

        BeanUtils.copyProperties(userPasswordDTO,userCopy);

        User user = findById(userPasswordDTO.getUser().getId());
        user.getUserDetailsENTITY().setPassword(userCopy.getUserDetailsENTITY().getPassword());

        return this.userRepository.save(user);
    }

    public User patchStatus(UserStatusDTO userStatusDTO){
        User userCopy = new User();

        BeanUtils.copyProperties(userStatusDTO,userCopy);

        User user = findById(userStatusDTO.getUser().getId());
        user.setStatus(userCopy.isStatus());
        return this.userRepository.save(user);
    }

    public void patchFile(Long id, MultipartFile photo) throws IOException {

        User user = findById(id);

        Archive archive = new Archive();
        archive.setName(photo.getOriginalFilename());
        archive.setType(photo.getContentType());
        archive.setData(photo.getBytes());

        user.setPhoto(archive);

        userRepository.save(user);

    }

    public void delete(Long idUser){
        if (idUser!=null && idUser>0){
            this.userRepository.deleteById(idUser);
        }
    }


    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
