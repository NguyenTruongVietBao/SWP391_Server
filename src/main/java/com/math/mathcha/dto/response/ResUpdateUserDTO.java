package com.math.mathcha.dto.response;


import com.math.mathcha.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResUpdateUserDTO {
    private int user_id;

    private String first_name;

    private String last_name;

    private String phone;

    private String email;

    private String address;

    private String image;

    private String username;

    private Role role;

}
