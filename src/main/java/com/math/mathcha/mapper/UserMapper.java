package com.math.mathcha.mapper;

import com.math.mathcha.dto.request.UserDTO;
import com.math.mathcha.entity.User;

public class UserMapper {
    public static UserDTO mapToUserDTO(User user){
        return new UserDTO(
                user.getUser_id(),
                user.getFirst_name(),
                user.getLast_name(),
                user.getPhone(),
                user.getEmail(),
                user.getAddress(),
                user.getImage(),
                user.getUsername(),
                user.getPassword(),
                user.getIs_deleted(),
                user.getRole_id()
        );

    }
    public static User mapToUser(UserDTO userDTO){
        return new User(
                userDTO.getUser_id(),
                userDTO.getFirst_name(),
                userDTO.getLast_name(),
                userDTO.getPhone(),
                userDTO.getEmail(),
                userDTO.getAddress(),
                userDTO.getImage(),
                userDTO.getUsername(),
                userDTO.getPassword(),
                userDTO.getIs_deleted(),
                userDTO.getRole_id()
        );

    }
}
