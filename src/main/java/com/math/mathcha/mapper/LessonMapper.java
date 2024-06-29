package com.math.mathcha.mapper;

import com.math.mathcha.dto.request.LessonDTO;
import com.math.mathcha.entity.Lesson;

public class LessonMapper {
    public static LessonDTO mapToLessonDTO(Lesson lesson){
        LessonDTO lessonDTO = new LessonDTO();
        lessonDTO.setLesson_id(lesson.getLesson_id());
        lessonDTO.setTitle(lesson.getTitle());
        lessonDTO.setNumber(lesson.getNumber());
        lessonDTO.setDocument(lesson.getDocument());
        lessonDTO.setVideo_url(lesson.getVideo_url());

        return lessonDTO;
    }

    public static Lesson mapToLesson(LessonDTO lessonDTO){
        Lesson lesson = new Lesson();
        lesson.setLesson_id(lessonDTO.getLesson_id());
        lesson.setTitle(lessonDTO.getTitle());
        lesson.setNumber(lessonDTO.getNumber());
        lesson.setDocument(lessonDTO.getDocument());
        lesson.setVideo_url(lessonDTO.getVideo_url());

        return lesson;
    }
}
