package com.math.mathcha.controller.QuizController;

import com.math.mathcha.dto.request.*;
import com.math.mathcha.dto.response.ResQuizResultDTO;
import com.math.mathcha.entity.Quiz;
import com.math.mathcha.service.quizService.QuizService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/quiz")
@SecurityRequirement(name = "api")
public class QuizController {

    private QuizService quizService;

    @GetMapping("/questions")
    @PreAuthorize("hasRole('CONTENT_MANAGER')")
    public List<QuestionDTO> getQuizQuestions() {
        return quizService.getQuizQuestions();
    }



    @PostMapping("/topic/{topicId}/generate")
    @PreAuthorize("hasAnyRole('CONTENT_MANAGER', 'STUDENT')")
    public Quiz generateQuizForTopic(@PathVariable int topicId,
                                     @RequestBody GenerateQuizRequest request) {
        return quizService.generateQuizForTopic(topicId, request.getNumberOfQuestions(), request.getTimeLimit());
    }


    @PostMapping("/chapter/{chapterId}/generate")
    @PreAuthorize("hasAnyRole('CONTENT_MANAGER', 'STUDENT')")
    public Quiz generateQuizForChapter(@PathVariable int chapterId,
                                       @RequestBody GenerateQuizRequest request) {
        return quizService.generateQuizForChapter(chapterId, request.getNumberOfQuestions(), request.getTimeLimit());
    }

    @PostMapping("/course/{courseId}/generate")
    @PreAuthorize("hasAnyRole('CONTENT_MANAGER', 'STUDENT')")
    public Quiz generateQuizForCourse(@PathVariable int courseId,
                                       @RequestBody GenerateQuizRequest request) {
        return quizService.generateQuizForCourse(courseId, request.getNumberOfQuestions(), request.getTimeLimit());
    }

    @PostMapping("/evaluate/enrollment/{enrollment_id}")
    @PreAuthorize("hasAnyRole('CONTENT_MANAGER', 'STUDENT')")
    public ResQuizResultDTO evaluateQuiz(
                                         @PathVariable int enrollment_id,
                                         @RequestBody EvaluateQuizRequest request) {
        return quizService.evaluateQuiz( enrollment_id, request.getQuizDTO());
    }

    @GetMapping("/results/{enrollment_id}")
    public ResponseEntity<List<QuizResultDTO>> getQuizResultsByEnrollmentId(@PathVariable int enrollment_id) {
        List<QuizResultDTO> quizResults = quizService.getQuizResultByEnrollmentId(enrollment_id);
        return ResponseEntity.ok(quizResults);
    }

    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('CONTENT_MANAGER', 'STUDENT')")
    public ResponseEntity<QuizResultDTO> saveQuiz(@RequestBody SaveQuizRequestDTO saveQuizRequestDTO) {
        int enrollment_id = saveQuizRequestDTO.getEnrollment_id();
        int score = saveQuizRequestDTO.getScore();
        String quiz_name = saveQuizRequestDTO.getQuiz_name();
        QuizResultDTO quizResultDTO = quizService.saveQuiz(enrollment_id, score, quiz_name );
        return ResponseEntity.ok(quizResultDTO);
    }

}

