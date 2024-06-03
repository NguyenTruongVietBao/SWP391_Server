//package com.math.primarySchoolMath.service.lessonService.Impl;
//
//import com.math.primarySchoolMath.dto.request.LessonDTO;
//import com.math.primarySchoolMath.entity.Lesson;
//import com.math.primarySchoolMath.mapper.CourseMapper;
//import com.math.primarySchoolMath.mapper.LessonMapper;
//import com.math.primarySchoolMath.repository.LessonRepository.LessonRepository;
//import com.math.primarySchoolMath.service.lessonService.LessonService;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@AllArgsConstructor
//public class LessonServiceImpl implements LessonService {
//
//    private LessonRepository lessonRepository;
//    @Override
//    public LessonDTO createLesson(LessonDTO lessonDTO) {
//        Lesson lesson = LessonMapper.mapToLesson(lessonDTO);
//        Lesson savedLesson= lessonRepository.save(lesson);
//        return LessonMapper.mapToLessonDTO(savedLesson);
//    }
//
//    @Override
//    public LessonDTO getLessonById(Integer lesson_id) {
//        Lesson lesson = lessonRepository.findById(lesson_id)
//                .orElseThrow(() -> new RuntimeException("Lesson "+lesson_id+" not found"));
//        return LessonMapper.mapToLessonDTO(lesson);
//    }
//
//    @Override
//    public List<LessonDTO> getLessonAll() {
//        List<Lesson> lessons = lessonRepository.findAll();
//        return lessons.stream().map(
//                (lesson) -> LessonMapper.mapToLessonDTO(lesson)).collect(Collectors.toList()
//        );
//    }
//
//    @Override
//    public LessonDTO updateLesson(LessonDTO updateLesson, Integer lesson_id) {
//        Lesson lesson = lessonRepository.findById(lesson_id)
//                .orElseThrow(()-> new RuntimeException("Lesson "+lesson_id+" not found"));
//        lesson.setTitle(updateLesson.getTitle());
//        lesson.setNumber(updateLesson.getNumber());
//        lesson.setDocument(updateLesson.getDocument());
//        lesson.setVideo_url(updateLesson.getVideo_url());
//        lesson.setTopic_id(updateLesson.getTopic_id());
//        Lesson updateLessonsObj = lessonRepository.save(lesson);
//        return  LessonMapper.mapToLessonDTO(updateLessonsObj);
//    }
//
//    @Override
//    public void deleteLesson(Integer lesson_id) {
//        Lesson lesson = lessonRepository.findById(lesson_id)
//                .orElseThrow(() -> new RuntimeException("Not exits"+lesson_id));
//        lessonRepository.deleteById(lesson_id);
//    }
//}
