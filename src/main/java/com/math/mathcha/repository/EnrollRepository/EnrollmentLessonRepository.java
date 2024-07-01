package com.math.mathcha.repository.EnrollRepository;

import com.math.mathcha.entity.Enrollments.EnrollmentLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface EnrollmentLessonRepository extends JpaRepository<EnrollmentLesson, Integer> {

}