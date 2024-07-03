package com.math.mathcha.repository;

import com.math.mathcha.dto.request.EnrollmentDTO;
import com.math.mathcha.entity.Enrollment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment,Integer> {
    @Query("SELECT t FROM Enrollment t WHERE t.student.student_id = :student_id")
    List<Enrollment> findByStudentId(@Param(value = "student_id") int student_id);
    @Query("SELECT e FROM Enrollment e WHERE e.student.student_id = :student_id AND e.course.course_id = :course_id")
    List<Enrollment> findEnrollmentsByStudentIdAndCourseId(@Param("student_id") int student_id, @Param("course_id") int course_id);
}
