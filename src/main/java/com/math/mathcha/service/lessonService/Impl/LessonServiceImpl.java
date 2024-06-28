package com.math.mathcha.service.lessonService.Impl;

import com.math.mathcha.Util.Error.IdInvalidException;
import com.math.mathcha.dto.request.LessonDTO;
import com.math.mathcha.dto.request.TopicDTO;
import com.math.mathcha.entity.Lesson;
import com.math.mathcha.entity.Topic;
import com.math.mathcha.mapper.LessonMapper;
import com.math.mathcha.mapper.TopicMapper;
import com.math.mathcha.repository.LessonRepository.LessonRepository;
import com.math.mathcha.repository.TopicRepository.TopicRepository;
import com.math.mathcha.service.lessonService.LessonService;
import com.math.mathcha.service.topicService.TopicService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LessonServiceImpl implements LessonService {

    private LessonRepository lessonRepository;
    private final TopicRepository topicRepository;
    private TopicService  topicService;

    @Override
    public LessonDTO createLesson(LessonDTO lessonDTO, Integer topic_id) {
        Lesson lesson = LessonMapper.mapToLesson(lessonDTO);
        Topic topic = topicRepository.findById(topic_id)
                .orElseThrow(() -> new RuntimeException("Topic: "+topic_id+" not found"));
        lesson.setTopic(topic);
        Lesson savedLesson= lessonRepository.save(lesson);
        return LessonMapper.mapToLessonDTO(savedLesson);
    }

    @Override
    public LessonDTO getLessonById(Integer lesson_id) throws IdInvalidException {
        Optional<Lesson> lesson = lessonRepository.findById(lesson_id);
        if (lesson.isPresent()) {
            return LessonMapper.mapToLessonDTO(lesson.get());
        }else{
                throw new IdInvalidException("Lesson với id = " + lesson_id + " không tồn tại");
        }
    }

    @Override
    public List<LessonDTO> getLessonsByTopicId(int topic_id) throws IdInvalidException {
        List<Lesson> lessons = lessonRepository.findLessonsByTopicId(topic_id);
        TopicDTO topicDTO = topicService.getTopicById(topic_id);
        if (topicDTO == null) {
            throw new IdInvalidException("Trong topic id = " + topic_id + " hiện không có lesson");
        }
        return lessons.stream().map(
                (lesson) -> LessonMapper.mapToLessonDTO(lesson)).collect(Collectors.toList()
        );
    }

//    @Override
//    public List<LessonDTO> getLessonAll() {
//        List<Lesson> lessons = lessonRepository.findAll();
//        return lessons.stream().map(
//                (lesson) -> LessonMapper.mapToLessonDTO(lesson)).collect(Collectors.toList()
//        );
//    }

    @Override
    public LessonDTO updateLesson(LessonDTO updateLesson, Integer lesson_id) {
        Lesson lesson = lessonRepository.findById(lesson_id)
                .orElseThrow(()-> new RuntimeException("Lesson "+lesson_id+" not found"));
        lesson.setTitle(updateLesson.getTitle());
        lesson.setNumber(updateLesson.getNumber());
        lesson.setDocument(updateLesson.getDocument());
        lesson.setVideo_url(updateLesson.getVideo_url());
        lesson.setIs_deleted(updateLesson.getIs_deleted());
        Lesson updateLessonsObj = lessonRepository.save(lesson);
        return  LessonMapper.mapToLessonDTO(updateLessonsObj);
    }

    @Override
    public void deleteLesson(Integer lesson_id) throws IdInvalidException {
        Lesson lesson = lessonRepository.findById(lesson_id)
                .orElseThrow(() -> new IdInvalidException("Lesson với id = " + lesson_id + " không tồn tại"));
        lessonRepository.deleteById(lesson_id);
    }
}
