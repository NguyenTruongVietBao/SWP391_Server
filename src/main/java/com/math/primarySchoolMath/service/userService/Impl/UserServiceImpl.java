package com.math.primarySchoolMath.service.userService.Impl;

import com.math.primarySchoolMath.dto.request.UserDTO;
import com.math.primarySchoolMath.entity.Student;
import com.math.primarySchoolMath.entity.User;
import com.math.primarySchoolMath.mapper.StudentMapper;
import com.math.primarySchoolMath.mapper.UserMapper;
import com.math.primarySchoolMath.repository.UserRepository.UserRepository;
import com.math.primarySchoolMath.service.userService.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = UserMapper.mapToUser(userDTO);
        User savedUser= userRepository.save(user);
        return UserMapper.mapToUserDTO(savedUser);
    }

    @Override
    public UserDTO getUserById(Integer user_id) {
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new RuntimeException("User "+user_id+" not found"));
        return UserMapper.mapToUserDTO(user);
    }

    @Override
    public List<UserDTO> getUserAll() {
            List<User> userss = userRepository.findAll();
            return userss.stream().map(
                    (user) -> UserMapper.mapToUserDTO(user)).collect(Collectors.toList()
            );
    }

    @Override
    public UserDTO updateUser(UserDTO updateUser, Integer user_id) {
        User user = userRepository.findById(user_id)
                .orElseThrow(()-> new RuntimeException("User "+user_id+" not found"));
        user.setFirst_name(updateUser.getFirst_name());
        user.setLast_name(updateUser.getLast_name());
        user.setPhone(updateUser.getPhone());
        user.setEmail(updateUser.getEmail());
        user.setAddress(updateUser.getAddress());
        user.setImage(updateUser.getImage());
        user.setUsername(updateUser.getUsername());
        user.setPassword(updateUser.getPassword());
        user.setIs_deleted(updateUser.getIs_deleted());
        user.setRole_id(updateUser.getRole_id());
        User updateUserObj = userRepository.save(user);
        return UserMapper.mapToUserDTO(updateUserObj);
    }

    @Override
    public void deleteUser(Integer user_id) {
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new RuntimeException("Not exits"+user_id));
        userRepository.deleteById(user_id);
    }
}
