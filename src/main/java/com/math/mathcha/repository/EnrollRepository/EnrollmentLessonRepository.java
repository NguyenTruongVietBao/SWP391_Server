package com.math.mathcha.repository.EnrollRepository;

import com.math.mathcha.entity.Enrollments.EnrollmentLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface EnrollmentLessonRepository extends JpaRepository<EnrollmentLesson, Integer> {
    @Query("SELECT e.is_complete FROM EnrollmentLesson e WHERE e.enrollment.enrollment_id = :enrollment_id AND e.lesson.lesson_id = :lesson_id")
    Boolean findIsCompleteByEnrollmentIdAndLessonId(@Param("enrollment_id") int enrollmentId, @Param("lesson_id") int lessonId);
}