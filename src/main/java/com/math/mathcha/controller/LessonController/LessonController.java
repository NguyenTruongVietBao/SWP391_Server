package com.math.mathcha.controller.LessonController;

import com.math.mathcha.Util.Error.IdInvalidException;
import com.math.mathcha.dto.request.LessonDTO;
import com.math.mathcha.dto.request.TopicDTO;
import com.math.mathcha.service.lessonService.LessonService;
import com.math.mathcha.service.topicService.TopicService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/lessons")
public class LessonController {
    private LessonService lessonService;
    private TopicService topicService ;
    @PreAuthorize("hasRole('CONTENT_MANAGER')")
    @PostMapping("/{topic_id}")
    public ResponseEntity<LessonDTO> createLesson(@RequestBody LessonDTO lessonDTO,
                                                  @PathVariable("topic_id") Integer topic_id){
        LessonDTO savedLesson = lessonService.createLesson(lessonDTO, topic_id);
        return new ResponseEntity<>(savedLesson, HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('CONTENT_MANAGER')")
    @GetMapping("/topic/{topic_id}")
    public ResponseEntity<List<LessonDTO>> getLessonsByTopicId(@PathVariable("topic_id") int topic_id) throws IdInvalidException {
        List<LessonDTO> lesson = lessonService.getLessonsByTopicId(topic_id);
        TopicDTO topicDTO = topicService.getTopicById(topic_id);
        if (topicDTO == null) {
            throw new IdInvalidException("Trong topic id = " + topic_id + " hiện không có lesson");
        }
        return ResponseEntity.ok(lesson);
    }
    @PreAuthorize("hasRole('CONTENT_MANAGER')")
    @GetMapping("/{lesson_id}")
    public ResponseEntity<LessonDTO> getLessonById (@PathVariable("lesson_id") Integer lesson_id) throws IdInvalidException {
        LessonDTO lessonDTO = lessonService.getLessonById(lesson_id);

        if (lessonDTO == null) {
            throw new IdInvalidException("Lesson với id = " + lesson_id + " không tồn tại");
        }
        return ResponseEntity.ok(lessonDTO);
    }

//    @GetMapping
//    public ResponseEntity<List<LessonDTO>> getLessonAll(){
//        List<LessonDTO> lesson = lessonService.getLessonAll();
//        return ResponseEntity.ok(lesson);
//    }
    @PreAuthorize("hasRole('CONTENT_MANAGER')")
    @PutMapping("/{lesson_id}")
    public ResponseEntity<LessonDTO> updateLesson (@RequestBody LessonDTO updatedLesson, @PathVariable("lesson_id") Integer lessonId){
        LessonDTO lessonDTO = lessonService.updateLesson(updatedLesson, lessonId );
        return ResponseEntity.ok(lessonDTO);
    }
    @PreAuthorize("hasRole('CONTENT_MANAGER')")
    @DeleteMapping("/{lesson_id}")
    public ResponseEntity<Void> deleteLesson(@PathVariable("lesson_id") Integer lesson_id) throws IdInvalidException {
        LessonDTO currentTopic = this.lessonService.getLessonById(lesson_id);
        if (currentTopic == null) {
            throw new IdInvalidException("Lesson với id = " + lesson_id + " không tồn tại");
        }

        this.lessonService.deleteLesson(lesson_id);
        return ResponseEntity.ok(null);
    }
}
