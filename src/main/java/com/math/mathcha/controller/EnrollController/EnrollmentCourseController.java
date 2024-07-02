package com.math.mathcha.controller.EnrollController;

import com.math.mathcha.Util.Error.IdInvalidException;
import com.math.mathcha.dto.request.EnrollmentCourseDTO;
import com.math.mathcha.dto.request.EnrollmentTopicDTO;
import com.math.mathcha.entity.Enrollments.EnrollmentCourse;
import com.math.mathcha.service.EnrollService.EnrollmentCourseService;
import com.math.mathcha.service.EnrollService.EnrollmentTopicService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/completeCourse")
@SecurityRequirement(name = "api")
public class EnrollmentCourseController {
    @Autowired
    private EnrollmentCourseService enrollmentCourseService;


    //    @PreAuthorize("hasRole('CONTENT_MANAGER')")
    @PostMapping("/create/{enrollment_id}/{course_id}")
    public ResponseEntity<EnrollmentCourseDTO> createEnrollmentCourse(@PathVariable int enrollment_id, @PathVariable int course_id) throws IdInvalidException {
        EnrollmentCourseDTO savedEnrollment = enrollmentCourseService.createEnrollmentCourse(enrollment_id, course_id);
        return new ResponseEntity<>(savedEnrollment, HttpStatus.CREATED);
    }
    @GetMapping("/status/{enrollment_id}/{course_id}")
    public ResponseEntity<Boolean> getIsComplete(@PathVariable("enrollment_id") int enrollmentId, @PathVariable("course_id") int lessonId) {
        Boolean isComplete = enrollmentCourseService.getIsCompleteByEnrollmentIdAndCourseId(enrollmentId, lessonId);
        return ResponseEntity.ok(isComplete);
    }
}
