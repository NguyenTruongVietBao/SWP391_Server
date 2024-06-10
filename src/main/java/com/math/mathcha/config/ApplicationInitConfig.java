package com.math.mathcha.config;


import com.math.mathcha.entity.User;
import com.math.mathcha.repository.UserRepository.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;


@Configuration
public class ApplicationInitConfig {
    private final PasswordEncoder passwordEncoder;

    public ApplicationInitConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository){
        return args -> {

            if (!userRepository.existsByUsername("admin1")) {
                User admin = new User();
                admin.setUsername("admin1");
                admin.setPassword(passwordEncoder.encode("admin"));
                admin.setRoles(Set.of("ROLE_ADMIN"));
                userRepository.save(admin);
            }




        };
    }


}
