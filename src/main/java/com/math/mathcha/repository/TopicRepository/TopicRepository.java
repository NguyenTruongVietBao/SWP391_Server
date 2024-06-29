package com.math.mathcha.repository.TopicRepository;

import com.math.mathcha.entity.Chapter;
import com.math.mathcha.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
@
public interface TopicRepository extends JpaRepository<Topic,Integer> {
    @Query("SELECT t FROM Topic t WHERE t.chapter.chapter_id = :chapter_id")
    List<Topic> findTopicsByChapterId(@Param(value = "chapter_id") int chapter_id);
}
