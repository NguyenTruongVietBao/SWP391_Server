package com.math.mathcha.mapper;

import com.math.mathcha.dto.request.LessonDTO;
import com.math.mathcha.entity.Lesson;

public class LessonMapper {
    public static LessonDTO mapToLessonDTO(Lesson lesson){
        return new LessonDTO(
                lesson.getLesson_id(),
                lesson.getTitle(),
                lesson.getNumber(),
                lesson.getDocument(),
                lesson.getVideo_url(),
                lesson.getTopic_id()
        );
    }

    public static Lesson mapToLesson(LessonDTO lessonDTO){
        return new Lesson(
                lessonDTO.getLesson_id(),
                lessonDTO.getTitle(),
                lessonDTO.getNumber(),
                lessonDTO.getDocument(),
                lessonDTO.getVideo_url(),
                lessonDTO.getTopic_id()
        );
    }
}
