package com.math.mathcha.service.userService.Impl;

import com.math.mathcha.dto.request.UserDTO;
import com.math.mathcha.dto.response.ResCreateUserDTO;
import com.math.mathcha.dto.response.ResUpdateUserDTO;
import com.math.mathcha.dto.response.ResUserDTO;
import com.math.mathcha.entity.User;
import com.math.mathcha.enums.Role;
import com.math.mathcha.mapper.UserMapper;
import com.math.mathcha.repository.UserRepository.UserRepository;
import com.math.mathcha.service.userService.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;


    @Override
    public UserDTO createUser(UserDTO userDTO) {

        User user = UserMapper.mapToUser(userDTO);

        user.setRole(userDTO.getRole());
        User savedUser= userRepository.save(user);
        return UserMapper.mapToUserDTO(savedUser);
    }

    @Override
    public UserDTO createContentManager(UserDTO userDTO) {

        User user = UserMapper.mapToUser(userDTO);
        user.setRole(user.getRole());
        User savedUser= userRepository.save(user);
        return UserMapper.mapToUserDTO(savedUser);
    }

    @Override
    public UserDTO getUserById(Integer user_id) {
        Optional<User> user = userRepository.findById(user_id);
        if (user.isPresent()) {
            return UserMapper.mapToUserDTO(user.get());
        }
        return null;
    }

    @Override
    public List<UserDTO> getUserAll() {
            List<User> userss = userRepository.findAll();
            return userss.stream().map(
                    (user) -> UserMapper.mapToUserDTO(user)).collect(Collectors.toList()
            );
    }

    @Override
    public UserDTO updateUser(UserDTO updateUser) {
        //UserDTO user = getUserById(updateUser.getUser_id());
        UserDTO user = getUserById(updateUser.getUser_id());
        user.setFirst_name(updateUser.getFirst_name());
        user.setLast_name(updateUser.getLast_name());
        user.setPhone(updateUser.getPhone());
        user.setEmail(updateUser.getEmail());
        user.setAddress(updateUser.getAddress());
        user.setImage(updateUser.getImage());
        user.setUsername(updateUser.getUsername());
        user.setPassword(updateUser.getPassword());
        user.setIs_deleted(updateUser.getIs_deleted());

        user.setRole(updateUser.getRole());
        User updatedUserEntity = UserMapper.mapToUser(user);
        User savedUser = userRepository.save(updatedUserEntity);

        return UserMapper.mapToUserDTO(savedUser);
    }

    @Override
    public void deleteUser(Integer user_id) {
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new RuntimeException("Not exits"+user_id));
        userRepository.deleteById(user_id);
    }

    @Override
    public UserDTO handleGetUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(()->new RuntimeException("Not exits"+username));
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found");
//        }

        return UserMapper.mapToUserDTO(user);
    }

    public boolean isUsernameExist(String username) {
        return this.userRepository.existsByUsername(username);
    }
    @Override
    public ResCreateUserDTO convertToResCreateUserDTO(UserDTO user) {
        ResCreateUserDTO res = new ResCreateUserDTO();
        res.setUser_id(user.getUser_id());
        res.setFirst_name(user.getFirst_name());
        res.setLast_name(user.getLast_name());
        res.setPhone(user.getPhone());
        res.setEmail(user.getEmail());
        res.setAddress(user.getAddress());
        res.setImage(user.getImage());
        res.setUsername(user.getUsername());
        res.setRole(user.getRole());
        return res;
    }
@Override
    public ResUpdateUserDTO convertToResUpdateUserDTO(UserDTO user) {
        ResUpdateUserDTO res = new ResUpdateUserDTO();
        res.setUser_id(user.getUser_id());
        res.setFirst_name(user.getFirst_name());
        res.setLast_name(user.getLast_name());
        res.setPhone(user.getPhone());
        res.setEmail(user.getEmail());
        res.setAddress(user.getAddress());
        res.setImage(user.getImage());
        res.setUsername(user.getUsername());
        res.setRole(user.getRole());
        return res;
    }
@Override
    public ResUserDTO convertToResUserDTO(UserDTO user) {
        ResUserDTO res = new ResUserDTO();
    res.setUser_id(user.getUser_id());
    res.setFirst_name(user.getFirst_name());
    res.setLast_name(user.getLast_name());
    res.setPhone(user.getPhone());
    res.setEmail(user.getEmail());
    res.setAddress(user.getAddress());
    res.setImage(user.getImage());


        return res;
    }
}
