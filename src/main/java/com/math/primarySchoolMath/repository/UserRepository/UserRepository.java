package com.math.primarySchoolMath.repository.UserRepository;


import com.math.primarySchoolMath.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUsername(String username);
}
