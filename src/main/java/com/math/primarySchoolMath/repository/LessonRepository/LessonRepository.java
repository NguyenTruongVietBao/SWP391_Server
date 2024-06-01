package com.math.primarySchoolMath.repository.LessonRepository;

import com.math.primarySchoolMath.entity.Chapter;
import com.math.primarySchoolMath.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson,Integer> {
}
