package com.math.mathcha.service.userService;

import com.math.mathcha.dto.request.UserDTO;
import com.math.mathcha.entity.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component("userDetailsService")
public class UserDetailCustom implements UserDetailsService {
    private final UserService userService;

    public UserDetailCustom(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO userDTO = this.userService.handleGetUserByUsername(username);
        if (userDTO == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        Set<SimpleGrantedAuthority> authorities = userDTO.getRoles().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());

        // Chuyển đổi UserDTO sang User
        User user = new User(
                userDTO.getUser_id(),
                userDTO.getFirst_name(),
                userDTO.getLast_name(),
                userDTO.getPhone(),
                userDTO.getEmail(),
                userDTO.getAddress(),
                userDTO.getImage(),
                userDTO.getUsername(),
                userDTO.getPassword(),
                userDTO.getIs_deleted() != null ? userDTO.getIs_deleted() : false,
                userDTO.getRoles()
        );

        return new CustomUserDetails(user);
    }
}
