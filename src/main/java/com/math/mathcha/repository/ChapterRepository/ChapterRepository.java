package com.math.mathcha.repository.ChapterRepository;

import com.math.mathcha.entity.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ChapterRepository extends JpaRepository <Chapter,Integer> {
    @Query("SELECT c FROM Chapter c WHERE c.course.course_id = :course_id")
    List<Chapter> findChaptersByCourseId(@Param(value = "course_id") int course_id);
}
