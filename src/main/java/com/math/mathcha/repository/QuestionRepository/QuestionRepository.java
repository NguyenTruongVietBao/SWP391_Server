package com.math.mathcha.repository.QuestionRepository;

import com.math.mathcha.entity.Chapter;
import com.math.mathcha.entity.Question.Question;
import com.math.mathcha.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    @Query("SELECT q FROM Question q WHERE q.topic.topic_id = :topic_id")
    List<Question> findQuestionByTopicId(@Param(value = "topic_id") int topic_id);

    @Query("SELECT q FROM Question q WHERE q.topic.chapter.chapter_id = :chapterId")
    List<Question> findQuestionsByChapterId(@Param("chapterId") int chapterId);

    List<Question> findByTopic(Topic topic);

//    List<Question> findByChapter(Chapter chapter);
}
