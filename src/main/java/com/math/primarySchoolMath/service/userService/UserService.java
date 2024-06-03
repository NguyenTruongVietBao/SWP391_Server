package com.math.primarySchoolMath.service.userService;


import com.math.primarySchoolMath.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);

    User getUserById ( Integer user_id);

    List<User> getUserAll();

    User updateUser (User reqUser);

    void deleteUser (Integer user_id);

    User handleGetUserByUsername(String username);
}
