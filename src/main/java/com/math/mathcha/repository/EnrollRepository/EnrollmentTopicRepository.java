package com.math.mathcha.repository.EnrollRepository;



import com.math.mathcha.entity.Enrollments.EnrollmentTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EnrollmentTopicRepository extends JpaRepository<EnrollmentTopic, Integer> {
    @Query("SELECT e.is_complete FROM EnrollmentTopic e WHERE e.enrollment.enrollment_id = :enrollment_id AND e.topic.topic_id = :topic_id")
    Boolean findIsCompleteByEnrollmentIdAndTopicId(@Param("enrollment_id") int enrollmentId, @Param("topic_id") int lessonId);
}
