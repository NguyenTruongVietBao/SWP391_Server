package com.math.mathcha.service.authService;


import com.math.mathcha.Util.Error.NotFoundException;
import com.math.mathcha.Util.SecurityUtil;
import com.math.mathcha.dto.request.LoginDTO;
import com.math.mathcha.dto.response.ResLoginDTO;
import com.math.mathcha.entity.User;
import com.math.mathcha.repository.UserRepository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    UserRepository userRepository;

    PasswordEncoder passwordEncoder;

    SecurityUtil securityUtil;


    public ResLoginDTO login(LoginDTO loginDTO) {
        User user = userRepository.findByUsername(loginDTO.getUsername()).orElseThrow(() -> new NotFoundException("User not found"));
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) throw new NotFoundException("Wrong password");

        String token = securityUtil.createToken(user);

        ResLoginDTO resLoginDTO = new ResLoginDTO();
        resLoginDTO.setToken(token);
        resLoginDTO.setUsername(user.getUsername());
        resLoginDTO.setRole(user.getRole());
        resLoginDTO.setEmail(user.getEmail());
        resLoginDTO.setAddress(user.getAddress());
        resLoginDTO.setPhone(user.getPhone());
        resLoginDTO.setFirst_name(user.getFirst_name());
        resLoginDTO.setLast_name(user.getLast_name());
        resLoginDTO.setImage(user.getImage());
        resLoginDTO.setIs_deleted(user.getIs_deleted());
        resLoginDTO.setUser_id(user.getUser_id());

        return resLoginDTO;
    }
}
