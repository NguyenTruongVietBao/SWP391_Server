package com.math.mathcha.dto.request;

import com.math.mathcha.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private int user_id;

    private String first_name;

    private String last_name;

    private String phone;

    private String email;

    private String address;

    private String image;

    private String username;

    private String password;

    private Boolean is_deleted;

    private Role role;
}
