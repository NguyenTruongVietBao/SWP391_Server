package com.math.primarySchoolMath.controller.LessonController;

import com.math.primarySchoolMath.dto.request.LessonDTO;
import com.math.primarySchoolMath.service.lessonService.LessonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/lesson")
public class LessonController {
    private LessonService lessonService;

    @PostMapping
    public ResponseEntity<LessonDTO> createLesson(@RequestBody LessonDTO lessonDTO){
        LessonDTO savedLesson = lessonService.createLesson(lessonDTO);
        return new ResponseEntity<>(savedLesson, HttpStatus.CREATED);
    }

    @GetMapping("{lesson_id}")
    public ResponseEntity<LessonDTO> getLessonById (@PathVariable("lesson_id") Integer lesson_id){
        LessonDTO lessonDTO = lessonService.getLessonById(lesson_id);
        return ResponseEntity.ok(lessonDTO);
    }

    @GetMapping
    public ResponseEntity<List<LessonDTO>> getLessonAll(){
        List<LessonDTO> lesson = lessonService.getLessonAll();
        return ResponseEntity.ok(lesson);
    }

    @PutMapping("/update/{lesson_id}")
    public ResponseEntity<LessonDTO> updateLesson (@RequestBody LessonDTO updatedLesson, @PathVariable("lesson_id") Integer lessonId){
        LessonDTO lessonDTO = lessonService.updateLesson(updatedLesson, lessonId );
        return ResponseEntity.ok(lessonDTO);
    }

    @DeleteMapping("{lesson_id}")
    public ResponseEntity<String> deleteLesson(@PathVariable("lesson_id") Integer lesson_id){
        lessonService.deleteLesson(lesson_id);
        return ResponseEntity.ok("Delete successfully !");
    }
}
