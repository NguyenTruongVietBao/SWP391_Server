package com.math.mathcha.repository.EnrollRepository;

import com.math.mathcha.entity.Enrollments.EnrollmentChapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EnrollmentChapterRepository extends JpaRepository<EnrollmentChapter, Integer> {
    @Query("SELECT e.is_complete FROM EnrollmentChapter e WHERE e.enrollment.enrollment_id = :enrollment_id AND e.chapter.chapter_id = :chapter_id")
    Boolean findIsCompleteByEnrollmentIdAndChapterId(@Param("enrollment_id") int enrollmentId, @Param("chapter_id") int lessonId);
}
