//package com.math.mathcha.controller.ProgressController;
//
//import com.math.mathcha.dto.request.CreateProgressDTO;
//import com.math.mathcha.entity.Progress;
//import com.math.mathcha.repository.ProgressRepository.ProgressRepository;
//import com.math.mathcha.service.ProgressService.ProgressService;
//import io.swagger.v3.oas.annotations.security.SecurityRequirement;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@CrossOrigin("*")
//@SecurityRequirement(name = "api")
//public class ProgressController {
//    @Autowired
//    private ProgressController progressController;
//    @Autowired
//    private ProgressRepository progressRepository;
//    @Autowired
//    private ProgressService progressService;
//
//    @PostMapping("/progress")
//    public ResponseEntity<?> createProgress(@RequestBody CreateProgressDTO createProgressDTO) {
//        Progress progress = progressService.createProgress(createProgressDTO);
//        return ResponseEntity.ok(progress);
//    }
//
//    @GetMapping("/progress/percent/courseId")
//    public ResponseEntity<?> percentCourse(@RequestParam int course_id, @RequestParam int chapter_id) {
//        double percent = progressService.percentCourse(course_id, chapter_id);
//        return ResponseEntity.ok(percent);
//    }
//
//}
