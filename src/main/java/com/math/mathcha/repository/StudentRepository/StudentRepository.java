package com.math.mathcha.repository.StudentRepository;

import com.math.mathcha.entity.Student;
import com.math.mathcha.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    Student findByUsername(String username);

    @Query("SELECT c FROM Student c WHERE c.user.user_id = :user_id")
    List<Student> findStudentsByUserId(@Param(value = "user_id") int user_id);
}
