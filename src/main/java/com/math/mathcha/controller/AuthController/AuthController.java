package com.math.mathcha.controller.AuthController;

import com.math.mathcha.Util.Error.IdInvalidException;
import com.math.mathcha.dto.request.LoginDTO;
import com.math.mathcha.dto.request.UserDTO;
import com.math.mathcha.dto.response.ResLoginDTO;
import com.math.mathcha.entity.User;
import com.math.mathcha.service.OtpService;
import com.math.mathcha.service.authService.AuthService;
import com.math.mathcha.service.userService.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("api")
@SecurityRequirement(name = "api")
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    OtpService otpService;


    private final UserService userService ;

    @PostMapping("/login")
    public ResponseEntity<ResLoginDTO> login(@Valid @RequestBody LoginDTO loginDTO) {
        ResLoginDTO res = authService.login(loginDTO);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }


    @PostMapping("/register")
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDTO userDTO) throws IdInvalidException {
//        boolean isOtpValid = otpService.validateOtp(userDTO.getPhone(), otp);
//        if (!isOtpValid) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
        User user = authService.register(userDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }


    @PostMapping("/login/student")
    public ResponseEntity<ResLoginDTO> loginStudent(@Valid @RequestBody LoginDTO loginDTO) {
        ResLoginDTO res = authService.loginStudent(loginDTO);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }


//    @PostMapping("/sendOtp")
//    public ResponseEntity<String> sendOtp(@RequestParam String phoneNumber) {
//        String otp = otpService.generateOtp(phoneNumber);
//        return new ResponseEntity<>("OTP sent successfully", HttpStatus.OK);
//    }
//
//    @PostMapping("/verifyOtp")
//    public ResponseEntity<String> verifyOtp(@RequestParam String phoneNumber, @RequestParam String otp) {
//        boolean isValid = otpService.validateOtp(phoneNumber, otp);
//        if (isValid) {
//            return new ResponseEntity<>("OTP verified successfully", HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("Invalid OTP", HttpStatus.BAD_REQUEST);
//        }
//    }
}
