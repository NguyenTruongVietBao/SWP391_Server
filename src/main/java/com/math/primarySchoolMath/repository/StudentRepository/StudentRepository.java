package com.math.primarySchoolMath.repository.StudentRepository;

import com.math.primarySchoolMath.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {
}
