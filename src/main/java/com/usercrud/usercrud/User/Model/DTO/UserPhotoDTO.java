package com.usercrud.usercrud.User.Model.DTO;

import com.usercrud.usercrud.Archive;
import com.usercrud.usercrud.User.Model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPhotoDTO {

    private User user;
    private Archive photo;


}
