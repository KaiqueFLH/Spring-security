package com.usercrud.usercrud.User.Model.DTO;

import com.usercrud.usercrud.User.Model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserStatusDTO {

    private User user;
    private boolean status;

}
