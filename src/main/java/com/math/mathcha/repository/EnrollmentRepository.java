package com.math.mathcha.repository;

import com.math.mathcha.entity.Enrollment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment,Integer> {
}
