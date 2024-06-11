package com.math.mathcha.controller.LessonController;

import com.math.mathcha.dto.request.LessonDTO;
import com.math.mathcha.service.lessonService.LessonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/lessons")
public class LessonController {
    private LessonService lessonService;

    @PostMapping("/create/{topic_id}")
    public ResponseEntity<LessonDTO> createLesson(@RequestBody LessonDTO lessonDTO,
                                                  @PathVariable("topic_id") Integer topic_id){
        LessonDTO savedLesson = lessonService.createLesson(lessonDTO, topic_id);
        return new ResponseEntity<>(savedLesson, HttpStatus.CREATED);
    }

    @GetMapping("/getbytopic/{topic_id}")
    public ResponseEntity<List<LessonDTO>> getLessonsByTopicId(@PathVariable("topic_id") int topic_id){
        List<LessonDTO> lesson = lessonService.getLessonsByTopicId(topic_id);
        return ResponseEntity.ok(lesson);
    }

    @GetMapping("/getbyid/{lesson_id}")
    public ResponseEntity<LessonDTO> getLessonById (@PathVariable("lesson_id") Integer lesson_id){
        LessonDTO lessonDTO = lessonService.getLessonById(lesson_id);
        return ResponseEntity.ok(lessonDTO);
    }

//    @GetMapping
//    public ResponseEntity<List<LessonDTO>> getLessonAll(){
//        List<LessonDTO> lesson = lessonService.getLessonAll();
//        return ResponseEntity.ok(lesson);
//    }

    @PutMapping("/update/{lesson_id}")
    public ResponseEntity<LessonDTO> updateLesson (@RequestBody LessonDTO updatedLesson, @PathVariable("lesson_id") Integer lessonId){
        LessonDTO lessonDTO = lessonService.updateLesson(updatedLesson, lessonId );
        return ResponseEntity.ok(lessonDTO);
    }

    @DeleteMapping("/delete/{lesson_id}")
    public ResponseEntity<String> deleteLesson(@PathVariable("lesson_id") Integer lesson_id){
        lessonService.deleteLesson(lesson_id);
        return ResponseEntity.ok("Delete successfully !");
    }
}
