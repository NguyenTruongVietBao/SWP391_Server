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
                user.getIs_deleted()
        );

    }
    public static User mapToUser(UserDTO userDTO){
        User user = new User();
            user.setUser_id(user.getUser_id());
            user.setUser_id(user.getUser_id());
            user.setFirst_name(userDTO.getFirst_name());
            user.setLast_name(userDTO.getLast_name());
            user.setPhone(userDTO.getPhone());
            user.setEmail(userDTO.getEmail());
            user.setAddress(userDTO.getAddress());
            user.setImage(userDTO.getImage());
            user.setUsername(userDTO.getUsername());
            user.setPassword(userDTO.getPassword());
            user.setIs_deleted(userDTO.getIs_deleted());
        return user;
    }
}
