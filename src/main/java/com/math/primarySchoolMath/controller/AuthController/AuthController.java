package com.math.primarySchoolMath.controller.AuthController;


import com.math.primarySchoolMath.dto.response.LoginDTO;
import lombok.AllArgsConstructor;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
public class AuthController {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
//    private final SecurityUtil securityUtil;

    @PostMapping("/login")
    public ResponseEntity<LoginDTO> login(@RequestBody LoginDTO loginDTO) {
//        // Nạp input gồm username/password vào Security
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
//        // xác thực người dùng => cần viết hàm loadUserByUsername
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

//         create a token
//        String access_token = this.securityUtil.createToken(authentication);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        ResLoginDTO res = new ResLoginDTO();
//        res.setAccessToken(access_token);
        return ResponseEntity.ok().body(loginDTO);
    }

}
