package com.math.mathcha.service.userService;

import com.math.mathcha.Util.Error.IdInvalidException;
import com.math.mathcha.dto.request.UserDTO;
import com.math.mathcha.dto.response.ResCreateUserDTO;
import com.math.mathcha.dto.response.ResUpdateUserDTO;
import com.math.mathcha.dto.response.ResUserDTO;
import com.math.mathcha.entity.User;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO) throws IdInvalidException;

    UserDTO getUserById ( Integer user_id) throws IdInvalidException;

    List<ResUserDTO> getUserAll();

    UserDTO updateUser (UserDTO userDTO) throws IdInvalidException;

    void deleteUser (Integer user_id) throws IdInvalidException;

    UserDTO handleGetUserByUsername(String username);

    boolean isUsernameExist (String username);

    ResCreateUserDTO convertToResCreateUserDTO(UserDTO user);

    ResUpdateUserDTO convertToResUpdateUserDTO(UserDTO user);

    ResUserDTO convertToResUserDTO(UserDTO user);
}
