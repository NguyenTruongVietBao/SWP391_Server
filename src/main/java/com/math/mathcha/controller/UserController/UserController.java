package com.math.mathcha.controller.UserController;

import com.math.mathcha.dto.request.UserDTO;
import com.math.mathcha.service.userService.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    int a = 2;
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
        UserDTO savedUser = userService.createUser(userDTO);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("{user_id}")
    public ResponseEntity<UserDTO> getUserById (@PathVariable("user_id") Integer user_id){
        UserDTO userDTO = userService.getUserById(user_id);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUserAll(){
        List<UserDTO> student = userService.getUserAll();
        return ResponseEntity.ok(student);
    }

    @PutMapping("/update/{user_id}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO updatedStudent, @PathVariable("user_id") Integer studentId){
        UserDTO userDTO = userService.updateUser(updatedStudent, studentId );
        return ResponseEntity.ok(userDTO);
    }

    @DeleteMapping("{user_id}")
    public ResponseEntity<String> deleteUser(@PathVariable("user_id") Integer user_id){
        userService.deleteUser(user_id);
        return ResponseEntity.ok("Delete successfully !");
    }
}
