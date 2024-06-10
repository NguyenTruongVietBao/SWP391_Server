package com.math.mathcha.service.authService;


import com.math.mathcha.Util.Error.NotFoundException;
import com.math.mathcha.Util.SecurityUtil;
import com.math.mathcha.dto.request.LoginDTO;
import com.math.mathcha.dto.request.UserDTO;
import com.math.mathcha.dto.response.ResLoginDTO;
import com.math.mathcha.entity.Student;
import com.math.mathcha.entity.User;
import com.math.mathcha.enums.Role;
import com.math.mathcha.repository.StudentRepository.StudentRepository;
import com.math.mathcha.repository.UserRepository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    UserRepository userRepository;
@Autowired
    PasswordEncoder passwordEncoder;
@Autowired
    SecurityUtil securityUtil;

@Autowired
    StudentRepository studentRepository;


    public ResLoginDTO login(LoginDTO loginDTO) {
        User user = userRepository.findByUsername(loginDTO.getUsername()).orElseThrow(() -> new NotFoundException("Account not found"));
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



    public User register(UserDTO userDTO) {
        User user = new User(userDTO.getUser_id(),userDTO.getFirst_name()
                ,userDTO.getLast_name(),userDTO.getPhone()
                ,userDTO.getEmail(),userDTO.getAddress()
                ,userDTO.getImage(),userDTO.getUsername()
                ,passwordEncoder.encode(userDTO.getPassword()),userDTO.getIs_deleted()
                , Role.PARENT);

        try {
            return userRepository.save(user);
        }catch (DataIntegrityViolationException e){
            if(e.getMessage().contains("user.UK_sb8bbouer5wak8vyiiy4pf2bx")) throw new DataIntegrityViolationException("Duplicate UserName");
            else if(e.getMessage().contains("user.UK_ob8kqyqqgmefl0aco34akdtpe"))throw new DataIntegrityViolationException("Duplicate Email");
            else throw new DataIntegrityViolationException("Duplicate Phone");
        }


    }
}