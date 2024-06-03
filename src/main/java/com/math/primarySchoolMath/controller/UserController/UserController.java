package com.math.primarySchoolMath.controller.UserController;


import com.math.primarySchoolMath.entity.User;
import com.math.primarySchoolMath.service.userService.UserService;
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

    private final PasswordEncoder passwordEncoder;
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        String hashPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);
        User savedUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("{user_id}")
    public ResponseEntity<User> getUserById (@PathVariable("user_id") Integer user_id){
        User user = userService.getUserById(user_id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> getUserAll(){
        List<User> student = userService.getUserAll();
        return ResponseEntity.ok(student);
    }

    @PutMapping("/update/{user_id}")
    public ResponseEntity<User> updateUser(@RequestBody User updatedStudent){
        User user = userService.updateUser(updatedStudent);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("{user_id}")
    public ResponseEntity<String> deleteUser(@PathVariable("user_id") Integer user_id){
        userService.deleteUser(user_id);
        return ResponseEntity.status(HttpStatus.OK).body("Delete successfully !");
    }
}
