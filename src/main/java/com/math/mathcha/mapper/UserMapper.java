package com.math.mathcha.mapper;

import com.math.mathcha.dto.request.StudentDTO;
import com.math.mathcha.dto.request.UserDTO;
import com.math.mathcha.entity.Student;
import com.math.mathcha.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserDTO mapToUserDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setUser_id(user.getUser_id());
        userDTO.setFirst_name(user.getFirst_name());
        userDTO.setLast_name(user.getLast_name());
        userDTO.setPhone(user.getPhone());
        userDTO.setEmail(user.getEmail());
        userDTO.setAddress(user.getAddress());
        userDTO.setImage(user.getImage());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setIs_deleted(user.getIs_deleted());
        userDTO.setRole(user.getRole());
//        userDTO.setStudents(user.getStudents());

        return userDTO;

    }
    public static User mapToUser(UserDTO userDTO){
        User user = new User();
        user.setUser_id(userDTO.getUser_id());
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
