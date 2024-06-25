package com.math.mathcha.dto.request;

import com.math.mathcha.entity.Course;
import com.math.mathcha.entity.Payment;
import com.math.mathcha.entity.Student;
import com.math.mathcha.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
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

    private Boolean delete;

    private Role role;


}
