package com.math.mathcha.controller.EnrollController;

import com.math.mathcha.Util.Error.IdInvalidException;
import com.math.mathcha.dto.request.EnrollmentChapterDTO;
import com.math.mathcha.dto.request.EnrollmentLessonDTO;
import com.math.mathcha.service.EnrollService.EnrollmentChapterService;
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
@RequestMapping("/completeChapter")
@SecurityRequirement(name = "api")
public class EnrollmetChapterController {
    @Autowired
    private EnrollmentChapterService enrollmentChapterService;


    //    @PreAuthorize("hasRole('CONTENT_MANAGER')")
    @PostMapping("/create/{enrollment_id}/{chapter_id}")
    public ResponseEntity<EnrollmentChapterDTO> createEnrollmentChapter(@PathVariable int enrollment_id, @PathVariable int chapter_id) throws IdInvalidException {
        EnrollmentChapterDTO savedEnrollment = enrollmentChapterService.createEnrollmentChapter(enrollment_id, chapter_id);
        return new ResponseEntity<>(savedEnrollment, HttpStatus.CREATED);
    }
    @GetMapping("/status/{enrollment_id}/{chapter_id}")
    public ResponseEntity<Boolean> getIsComplete(@PathVariable("enrollment_id") int enrollmentId, @PathVariable("chapter_id") int lessonId) {
        Boolean isComplete = enrollmentChapterService.getIsCompleteByEnrollmentIdAndChapterId(enrollmentId, lessonId);
        return ResponseEntity.ok(isComplete);
    }
}
