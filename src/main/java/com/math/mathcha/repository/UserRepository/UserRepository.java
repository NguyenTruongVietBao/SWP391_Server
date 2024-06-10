package com.math.mathcha.repository.UserRepository;

import com.math.mathcha.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
}
