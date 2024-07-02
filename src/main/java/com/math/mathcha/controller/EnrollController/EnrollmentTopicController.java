package com.math.mathcha.controller.EnrollController;

import com.math.mathcha.Util.Error.IdInvalidException;
import com.math.mathcha.dto.request.EnrollmentTopicDTO;
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
@RequestMapping("/completeTopic")
@SecurityRequirement(name = "api")
public class EnrollmentTopicController {
    @Autowired
    private EnrollmentTopicService enrollmentTopicService;


    //    @PreAuthorize("hasRole('CONTENT_MANAGER')")
    @PostMapping("/create/{enrollment_id}/{topic_id}")
    public ResponseEntity<EnrollmentTopicDTO> createEnrollmentTopic(@PathVariable int enrollment_id, @PathVariable int topic_id) throws IdInvalidException {
        EnrollmentTopicDTO savedEnrollment = enrollmentTopicService.createEnrollmentTopic(enrollment_id, topic_id);
        return new ResponseEntity<>(savedEnrollment, HttpStatus.CREATED);
    }
    @GetMapping("/status/{enrollment_id}/{topic_id}")
    public ResponseEntity<Boolean> getIsComplete(@PathVariable("enrollment_id") int enrollmentId, @PathVariable("topic_id") int lessonId) {
        Boolean isComplete = enrollmentTopicService.getIsCompleteByEnrollmentIdAndTopicId(enrollmentId, lessonId);
        return ResponseEntity.ok(isComplete);
    }
}
