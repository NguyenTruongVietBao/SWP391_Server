package com.math.mathcha.service.authService;


import com.math.mathcha.Util.Error.IdInvalidException;
import com.math.mathcha.Util.Error.NotFoundException;
import com.math.mathcha.Util.SecurityUtil;
import com.math.mathcha.dto.request.LoginDTO;
import com.math.mathcha.dto.request.UserDTO;
import com.math.mathcha.dto.response.ResLoginDTO;
import com.math.mathcha.entity.User;
import com.math.mathcha.enums.Role;
import com.math.mathcha.repository.StudentRepository.StudentRepository;
import com.math.mathcha.repository.UserRepository.UserRepository;
import com.math.mathcha.service.userService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//import static jdk.internal.org.jline.reader.impl.LineReaderImpl.CompletionType.List;

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
    UserService userService;

@Autowired
    StudentRepository studentRepository ;

    public ResLoginDTO login(LoginDTO loginDTO) {
        var user = userRepository.findByUsername(loginDTO.getUsername()).orElseThrow(() -> new NotFoundException("User not found"));
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

    public ResLoginDTO studentLogin(LoginDTO loginDTO) {
        var student = studentRepository.findByUsername(loginDTO.getUsername()).orElseThrow(() -> new NotFoundException("User not found"));
        if (!passwordEncoder.matches(loginDTO.getPassword(), student.getPassword())) throw new NotFoundException("Wrong password");
        String token = securityUtil.createTokenStudent(student);
        ResLoginDTO resLoginDTO = new ResLoginDTO();
        resLoginDTO.setToken(token);
        resLoginDTO.setUsername(student.getUsername());
        resLoginDTO.setRole(Role.STUDENT);
        resLoginDTO.setEmail(student.getEmail());
        resLoginDTO.setAddress(student.getAddress());
        resLoginDTO.setPhone(student.getPhone());
        resLoginDTO.setFirst_name(student.getFirst_name());
        resLoginDTO.setLast_name(student.getLast_name());
        resLoginDTO.setImage(student.getImage());
        resLoginDTO.setIs_deleted(student.getIs_deleted());
        resLoginDTO.setUser_id(student.getStudent_id());
        return resLoginDTO;
    }

    public User register(UserDTO userDTO) throws IdInvalidException {
        User user = new User();
        user.setRole(Role.PARENT);
        user.setEmail(userDTO.getEmail());
        user.setAddress(userDTO.getAddress());
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setLast_name(userDTO.getLast_name());
        user.setPhone(userDTO.getPhone());
        user.setFirst_name(userDTO.getFirst_name());
        user.setImage(userDTO.getImage());
        user.setIs_deleted(userDTO.getDelete());
        user.setUser_id(userDTO.getUser_id());
        boolean isUsernameExist = this.userService.isUsernameExist(userDTO.getUsername());
        if (isUsernameExist) {
            throw new IdInvalidException(
                    "Username " + userDTO.getUsername() + " đã tồn tại, vui lòng sử dụng email khác.");
        }

        try {
            return userRepository.save(user);
        }catch (DataIntegrityViolationException e){
            if(e.getMessage().contains("user.UK_sb8bbouer5wak8vyiiy4pf2bx")) throw new DataIntegrityViolationException("Duplicate UserName");
            else if(e.getMessage().contains("user.UK_ob8kqyqqgmefl0aco34akdtpe"))throw new DataIntegrityViolationException("Duplicate Email");
            else throw new DataIntegrityViolationException("Duplicate Phone");
        }


    }
    public ResLoginDTO loginStudent(LoginDTO loginDTO) {
        var student = studentRepository.findByUsername(loginDTO.getUsername()).orElseThrow(() -> new NotFoundException("User not found"));
        if (!passwordEncoder.matches(loginDTO.getPassword(), student.getPassword())) throw new NotFoundException("Wrong password");
        String token = securityUtil.createTokenStudent(student);
        ResLoginDTO resLoginDTO = new ResLoginDTO();
        resLoginDTO.setToken(token);
        resLoginDTO.setRole(Role.STUDENT);
        resLoginDTO.setUsername(student.getUsername());
        resLoginDTO.setEmail(student.getEmail());
        resLoginDTO.setAddress(student.getAddress());
        resLoginDTO.setPhone(student.getPhone());
        resLoginDTO.setFirst_name(student.getFirst_name());
        resLoginDTO.setLast_name(student.getLast_name());
        resLoginDTO.setImage(student.getImage());
        resLoginDTO.setIs_deleted(student.getIs_deleted());
        resLoginDTO.setUser_id(student.getStudent_id());
        return resLoginDTO;
    }
}
