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

    @PostMapping("/create")
//    @PreAuthorize("hasRole('CONTENT_MANAGER')")
    public ResponseEntity<EnrollmentLessonDTO> createEnrollmentLesson(@RequestBody EnrollmentLessonDTO enrollmentLessonDTO) throws IdInvalidException {
        EnrollmentLessonDTO savedEnrollment = enrollmentLessonService.createEnrollmentLesson(enrollmentLessonDTO);
        return new ResponseEntity<>(savedEnrollment, HttpStatus.CREATED);
    }
}
