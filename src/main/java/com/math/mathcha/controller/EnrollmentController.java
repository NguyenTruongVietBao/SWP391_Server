package com.math.mathcha.controller;

import com.math.mathcha.Util.Error.IdInvalidException;
import com.math.mathcha.dto.request.EnrollmentDTO;
import com.math.mathcha.service.EnrollmentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/enrollment")
@SecurityRequirement(name = "api")
public class EnrollmentController {
    private EnrollmentService enrollmentService;
    @PostMapping("/create")
//    @PreAuthorize("hasRole('CONTENT_MANAGER')")
    public ResponseEntity<EnrollmentDTO> createEnrollment (@RequestBody EnrollmentDTO enrollmentDTO) throws IdInvalidException {
        EnrollmentDTO savedEnrollment = enrollmentService.createEnrollment(enrollmentDTO);
        return new ResponseEntity<>(savedEnrollment, HttpStatus.CREATED);
    }
}
