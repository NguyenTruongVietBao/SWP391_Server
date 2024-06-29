package com.math.mathcha.repository.EnrollRepository;

import com.math.mathcha.entity.Enrollments.EnrollmentLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface EnrollmentLessonRepository extends JpaRepository<EnrollmentLesson, Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE EnrollmentLesson e SET e.is_complete = true WHERE e.enrollmentLesson_id = :enrollmentLesson_id AND e.enrollment.student.student_id = :student_id")
    void markLessonComplete(@Param("enrollmentLesson_id") int enrollmentLesson_id, @Param("student_id") int student_id);
}