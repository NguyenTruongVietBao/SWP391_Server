package com.math.primarySchoolMath.service.userService;

import com.math.primarySchoolMath.dto.request.StudentDTO;
import com.math.primarySchoolMath.dto.request.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);

    UserDTO getUserById ( Integer user_id);

    List<UserDTO> getUserAll();

    UserDTO updateUser (UserDTO userDTO, Integer user_id);

    void deleteUser (Integer user_id);
}
