package com.math.mathcha.repository.EnrollRepository;

import com.math.mathcha.entity.Enrollments.EnrollmentCourse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EnrollmentCourseRepository extends JpaRepository<EnrollmentCourse, Integer> {
    @Query("SELECT e.is_complete FROM EnrollmentCourse e WHERE e.enrollment.enrollment_id = :enrollment_id AND e.course.course_id = :course_id")
    Boolean findIsCompleteByEnrollmentIdAndCourseId(@Param("enrollment_id") int enrollmentId, @Param("course_id") int lessonId);
}
