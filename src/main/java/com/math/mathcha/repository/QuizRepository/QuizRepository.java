package com.math.mathcha.repository.QuizRepository;

import com.math.mathcha.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {
    @Query("SELECT q FROM Quiz q WHERE q.topic.topic_id = :topic_id")
    List<Quiz> findQuizByTopicId(@Param(value = "topic_id") int topic_id);
}
