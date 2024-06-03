package com.math.primarySchoolMath.repository.UserRepository;


import com.math.primarySchoolMath.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUsername(String username);
}
