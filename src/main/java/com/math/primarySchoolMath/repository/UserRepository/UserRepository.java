package com.math.primarySchoolMath.repository.UserRepository;

import com.math.primarySchoolMath.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
