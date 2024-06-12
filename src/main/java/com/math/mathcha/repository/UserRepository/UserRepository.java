package com.math.mathcha.repository.UserRepository;

import com.math.mathcha.entity.Chapter;
import com.math.mathcha.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
   Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
}

