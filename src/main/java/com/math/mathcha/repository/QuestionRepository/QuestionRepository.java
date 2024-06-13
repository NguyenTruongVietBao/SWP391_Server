package com.math.mathcha.repository.QuestionRepository;

import com.math.mathcha.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    @Query("SELECT q FROM Question q WHERE q.topic.topic_id = :topic_id")
    List<Question> findQuestionByTopicId(@Param(value = "topic_id") int topic_id);
}
