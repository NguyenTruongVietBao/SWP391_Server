package com.math.primarySchoolMath.service.userService.Impl;



import com.math.primarySchoolMath.entity.User;
import com.math.primarySchoolMath.repository.UserRepository.UserRepository;
import com.math.primarySchoolMath.service.userService.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public User getUserById(Integer user_id) {
        Optional<User> userOptional = this.userRepository.findById(user_id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }
        return null;
    }

    @Override
    public List<User> getUserAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User updateUser(User reqUser) {
        User currentUser = this.getUserById(reqUser.getUser_id());
        if (currentUser != null) {
            currentUser.setFirst_name(reqUser.getFirst_name());
            currentUser.setLast_name(reqUser.getLast_name());
            currentUser.setPhone(reqUser.getPhone());
            currentUser.setEmail(reqUser.getEmail());
            currentUser.setAddress(reqUser.getAddress());
            currentUser.setImage(reqUser.getImage());
            currentUser.setUsername(reqUser.getUsername());
            currentUser.setPassword(reqUser.getPassword());
            currentUser.setIs_deleted(reqUser.getIs_deleted());
            currentUser.setRole_id(reqUser.getRole_id());
            // update
            currentUser = this.userRepository.save(currentUser);
        }
        return currentUser;
    }

    @Override
    public void deleteUser(Integer user_id) {
        this.userRepository.deleteById(user_id);
    }

    @Override
    public User handleGetUserByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }
}
