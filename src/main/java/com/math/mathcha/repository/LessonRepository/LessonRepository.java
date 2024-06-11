package com.math.mathcha.repository.LessonRepository;

import com.math.mathcha.entity.Lesson;
import com.math.mathcha.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson,Integer> {
    @Query("SELECT l FROM Lesson l WHERE l.topic.topic_id = :topic_id")
    List<Lesson> findLessonsByTopicId(@Param(value = "topic_id") int topic_id);
}
