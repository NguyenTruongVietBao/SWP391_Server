package com.math.mathcha.repository.ProgressRepository;

import com.math.mathcha.entity.Progress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgressRepository extends JpaRepository<Progress, Long> {
    List<Progress> findProgressByStudentId(long student_id);

    List<Progress> findProgressByCourseIdAndStudentId(Long course_id, Long student_id);

    Progress findProgressByLessonIdAndStudentId(int lesson_id, int student_id);
}
