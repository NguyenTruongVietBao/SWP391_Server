package com.math.mathcha.controller.UserController;

import com.math.mathcha.Util.Error.IdInvalidException;
import com.math.mathcha.dto.request.UserDTO;
import com.math.mathcha.dto.response.ResCreateUserDTO;
import com.math.mathcha.dto.response.ResUpdateUserDTO;
import com.math.mathcha.dto.response.ResUserDTO;
import com.math.mathcha.service.userService.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    private PasswordEncoder passwordEncoder;
    @PostMapping

    public ResponseEntity<ResCreateUserDTO> createUser(@Valid @RequestBody UserDTO userDTO) throws IdInvalidException {
        boolean isUsernameExist = this.userService.isUsernameExist(userDTO.getUsername());
        if (isUsernameExist) {
            throw new IdInvalidException(
                    "Username " + userDTO.getUsername() + " đã tồn tại, vui lòng sử dụng email khác.");
        }

        String hashPassword = this.passwordEncoder.encode(userDTO.getPassword());
        userDTO.setPassword(hashPassword);
        UserDTO savedUser = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.convertToResCreateUserDTO(savedUser));
    }

    @GetMapping("{user_id}")
    public ResponseEntity<ResUserDTO> getUserById (@PathVariable("user_id") Integer user_id) throws IdInvalidException{
        UserDTO userDTO = userService.getUserById(user_id);
        if (userDTO == null) {
            throw new IdInvalidException("User với id = " + user_id + " không tồn tại");
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(this.userService.convertToResUserDTO(userDTO));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUserAll(){
        List<UserDTO> student = userService.getUserAll();
        return ResponseEntity.ok(student);
    }

    @PutMapping("/update")
    public ResponseEntity<ResUpdateUserDTO> updateUser(@RequestBody UserDTO updatedUser) throws IdInvalidException{
        UserDTO userDTO = userService.updateUser(updatedUser);
        if (userDTO == null) {
            throw new IdInvalidException("User với id = " + updatedUser.getUser_id() + " không tồn tại");
        }
        return ResponseEntity.ok(this.userService.convertToResUpdateUserDTO(userDTO));
    }

    @DeleteMapping("{user_id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("user_id") Integer user_id) throws IdInvalidException{
        UserDTO currentUser = this.userService.getUserById(user_id);
        if (currentUser == null) {
            throw new IdInvalidException("User với id = " + user_id + " không tồn tại");
        }

        this.userService.deleteUser(user_id);
        return ResponseEntity.ok(null);
    }
}
