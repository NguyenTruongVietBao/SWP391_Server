package com.math.mathcha.repository.StudentRepository;

import com.math.mathcha.entity.Student;
import com.math.mathcha.entity.Topic;
import com.math.mathcha.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    @Query("SELECT t FROM Student t WHERE t.user.user_id = :user_id")
    List<Student> findStudentByUserId(@Param(value = "user_id") int user_id);
    boolean existsByUsername(String username);


}
