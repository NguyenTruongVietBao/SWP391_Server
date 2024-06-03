package com.math.mathcha.repository.StudentRepository;

import com.math.mathcha.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {
}
