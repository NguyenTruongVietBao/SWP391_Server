package com.math.mathcha.controller;

import com.math.mathcha.dto.request.LoginDTO;
import com.math.mathcha.dto.response.ResLoginDTO;
import com.math.mathcha.service.authService.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("api")
public class AuthController {

    @Autowired
    AuthService authService;




    @PostMapping("/login")
    public ResponseEntity<ResLoginDTO> login(@Valid @RequestBody LoginDTO loginDTO) {
            ResLoginDTO res = authService.login(loginDTO);
            return ResponseEntity.ok().body(res);
    }
}
