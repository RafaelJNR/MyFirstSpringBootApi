package com.bbsw.myFirstApi.user.dto;

import com.bbsw.myFirstApi.user.enums.RolEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    String username;
    String password;
    RolEnum rol;

}
