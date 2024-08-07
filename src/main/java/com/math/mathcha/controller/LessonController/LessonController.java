package com.math.mathcha.controller.LessonController;

import com.math.mathcha.Util.Error.IdInvalidException;
import com.math.mathcha.dto.request.LessonDTO;
import com.math.mathcha.dto.request.TopicDTO;
import com.math.mathcha.entity.Topic;
import com.math.mathcha.service.lessonService.LessonService;
import com.math.mathcha.service.topicService.TopicService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@SecurityRequirement(name = "api")
public class LessonController {
    private LessonService lessonService;
    private TopicService topicService ;

    @PostMapping("/{topic_id}")
    @PreAuthorize("hasRole('CONTENT_MANAGER')")
    public ResponseEntity<LessonDTO> createLesson(@RequestBody LessonDTO lessonDTO,
                                                  @PathVariable("topic_id") Integer topic_id){
        LessonDTO savedLesson = lessonService.createLesson(lessonDTO, topic_id);
        return new ResponseEntity<>(savedLesson, HttpStatus.CREATED);
    }

    @GetMapping("/topic/{topic_id}")

    public ResponseEntity<List<LessonDTO>> getLessonsByTopicId(@PathVariable("topic_id") int topic_id) throws IdInvalidException {
        List<LessonDTO> lesson = lessonService.getLessonsByTopicId(topic_id);
        return ResponseEntity.ok(lesson);
    }

    @GetMapping("/{lesson_id}")
    @PreAuthorize("hasAnyRole('CONTENT_MANAGER', 'STUDENT','MANAGER')")
    public ResponseEntity<LessonDTO> getLessonById (@PathVariable("lesson_id") Integer lesson_id) throws IdInvalidException {
        LessonDTO lessonDTO = lessonService.getLessonById(lesson_id);
        return ResponseEntity.ok(lessonDTO);
    }

//    @GetMapping
//    public ResponseEntity<List<LessonDTO>> getLessonAll(){
//        List<LessonDTO> lesson = lessonService.getLessonAll();
//        return ResponseEntity.ok(lesson);
//    }

    @PutMapping("/{lesson_id}")
    @PreAuthorize("hasAnyRole('CONTENT_MANAGER', 'STUDENT')")
    public ResponseEntity<LessonDTO> updateLesson (@RequestBody LessonDTO updatedLesson, @PathVariable("lesson_id") Integer lessonId){
        LessonDTO lessonDTO = lessonService.updateLesson(updatedLesson, lessonId );
        return ResponseEntity.ok(lessonDTO);
    }

    @DeleteMapping("/{lesson_id}")
    @PreAuthorize("hasRole('CONTENT_MANAGER')")
    public ResponseEntity<Void> deleteLesson(@PathVariable("lesson_id") Integer lesson_id) throws IdInvalidException {
        LessonDTO currentTopic = this.lessonService.getLessonById(lesson_id);
        this.lessonService.deleteLesson(lesson_id);
        return ResponseEntity.ok(null);
    }
    @GetMapping("/{lesson_id}/topic")
    @PreAuthorize("hasAnyRole('CONTENT_MANAGER', 'STUDENT')") // can xem lai
    public ResponseEntity<TopicDTO> getTopicByLessonId(@PathVariable("lesson_id") int lessonId) {
        TopicDTO topicDTO = lessonService.getTopicByLessonId(lessonId);
        return ResponseEntity.ok(topicDTO);
    }

}
