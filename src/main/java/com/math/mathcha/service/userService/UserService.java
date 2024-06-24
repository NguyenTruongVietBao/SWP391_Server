package com.math.mathcha.service.userService;

import com.math.mathcha.Util.Error.IdInvalidException;
import com.math.mathcha.dto.request.UserDTO;
import com.math.mathcha.dto.response.ResCreateUserDTO;
import com.math.mathcha.dto.response.ResUpdateUserDTO;
import com.math.mathcha.dto.response.ResUserDTO;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO) throws IdInvalidException;

    UserDTO getUserById ( Integer user_id) throws IdInvalidException;

    List<ResUserDTO> getUserAll();

    UserDTO updateAdmin(UserDTO updateAdmin, Integer user_id);

    UserDTO updateContentManager(UserDTO updateContentManager, Integer user_id);

    UserDTO updateManager(UserDTO updateManager, Integer user_id);

    UserDTO updateParent(UserDTO updateParent, Integer user_id);

    void deleteUser (Integer user_id) throws IdInvalidException;

    UserDTO handleGetUserByUsername(String username);

    boolean isUsernameExist (String username);

    ResCreateUserDTO convertToResCreateUserDTO(UserDTO user);

    ResUpdateUserDTO convertToResUpdateUserDTO(UserDTO user);

    ResUserDTO convertToResUserDTO(UserDTO user);
}
