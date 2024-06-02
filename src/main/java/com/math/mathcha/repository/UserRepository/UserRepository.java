package com.math.mathcha.repository.UserRepository;

import com.math.mathcha.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
