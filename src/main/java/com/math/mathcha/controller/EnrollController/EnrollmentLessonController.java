package com.math.mathcha.controller.EnrollController;

import com.math.mathcha.Util.Error.IdInvalidException;
import com.math.mathcha.dto.request.EnrollmentDTO;
import com.math.mathcha.dto.request.EnrollmentLessonDTO;
import com.math.mathcha.service.EnrollService.EnrollmentLessonService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/completeLesson")
@SecurityRequirement(name = "api")
public class EnrollmentLessonController {
    @Autowired
    private EnrollmentLessonService enrollmentLessonService;


//    @PreAuthorize("hasRole('CONTENT_MANAGER')")
    @PostMapping("/create/{enrollment_id}/{lesson_id}")
    public ResponseEntity<EnrollmentLessonDTO> createEnrollmentLesson(@PathVariable int enrollment_id, @PathVariable int lesson_id) throws IdInvalidException {
        EnrollmentLessonDTO savedEnrollment = enrollmentLessonService.createEnrollmentLesson(enrollment_id, lesson_id);
        return new ResponseEntity<>(savedEnrollment, HttpStatus.CREATED);
    }
    @GetMapping("/status/{enrollment_id}/{lesson_id}")
    public ResponseEntity<Boolean> getIsComplete(@PathVariable("enrollment_id") int enrollmentId, @PathVariable("lesson_id") int lessonId) {
        Boolean isComplete = enrollmentLessonService.getIsCompleteByEnrollmentIdAndLessonId(enrollmentId, lessonId);
        return ResponseEntity.ok(isComplete);
    }

}
