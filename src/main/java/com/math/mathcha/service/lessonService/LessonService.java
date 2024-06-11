package com.math.mathcha.service.lessonService;

import com.math.mathcha.dto.request.LessonDTO;

import java.util.List;

public interface LessonService {
    LessonDTO createLesson(LessonDTO lessonDTO, Integer topic_id);

    LessonDTO getLessonById ( Integer lesson_id);

    List<LessonDTO> getLessonsByTopicId(int topic_id);

    LessonDTO updateLesson (LessonDTO lessonDTO, Integer lesson_id);

    void deleteLesson (Integer lesson_id);


}
