package com.math.mathcha.controller.EnrollController;

import com.math.mathcha.service.EnrollService.EnrollmentLessonService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/completeLesson")
@SecurityRequirement(name = "api")
public class EnrollmentLessonController {
    @Autowired
    private EnrollmentLessonService enrollmentLessonService;

    @PostMapping("/{enrollmentLesson_id}")
    public void completeLesson(@PathVariable("enrollmentLesson_id") int enrollmentLesson_id, @RequestParam("student_id") int student_id) {
        enrollmentLessonService.markLessonComplete(enrollmentLesson_id, student_id);
    }
}
