package com.usercrud.usercrud.User.Model.DTO;

import com.usercrud.usercrud.Archive;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.Serializable;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class UserCreateDTO implements Serializable {
    @NonNull
    private String name;
    @NonNull
    private String email;
    @NonNull
    private String password;
    @NonNull
    private Integer age;

    private Archive photo = new Archive();

    public void setPhoto(MultipartFile multipartFile) throws IOException {
        this.photo.setName(multipartFile.getOriginalFilename());
        this.photo.setType(multipartFile.getContentType());
        this.photo.setData(multipartFile.getBytes());
    }
}
