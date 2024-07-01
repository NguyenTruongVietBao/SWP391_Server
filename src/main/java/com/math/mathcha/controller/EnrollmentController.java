package com.math.mathcha.controller;

import com.math.mathcha.Util.Error.IdInvalidException;
import com.math.mathcha.dto.request.EnrollmentDTO;
import com.math.mathcha.entity.Enrollment;
import com.math.mathcha.service.EnrollmentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/enrollment")
@SecurityRequirement(name = "api")
public class EnrollmentController {
    private EnrollmentService enrollmentService;
    @PostMapping("/create")
//    @PreAuthorize("hasRole('CONTENT_MANAGER')")
    public ResponseEntity<EnrollmentDTO> createEnrollment(@RequestBody EnrollmentDTO enrollmentDTO) throws IdInvalidException {
        EnrollmentDTO savedEnrollment = enrollmentService.createEnrollment(enrollmentDTO);
        return new ResponseEntity<>(savedEnrollment, HttpStatus.CREATED);
    }

    @GetMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<List<EnrollmentDTO>> getEnrollmentsByStudentIdAndCourseId(
            @PathVariable int studentId, @PathVariable int courseId) {
        List<EnrollmentDTO> enrollments = enrollmentService.getEnrollmentsByStudentIdAndCourseId(studentId, courseId);
        return ResponseEntity.ok(enrollments);
    }
}
