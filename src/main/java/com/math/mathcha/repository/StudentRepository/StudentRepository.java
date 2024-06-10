package com.math.mathcha.repository.StudentRepository;

import com.math.mathcha.entity.Student;
import com.math.mathcha.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    Student findByUsername(String username);
}
