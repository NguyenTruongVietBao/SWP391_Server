package com.math.mathcha.controller.QuizController;

import com.math.mathcha.dto.request.EvaluateQuizRequest;
import com.math.mathcha.dto.request.GenerateQuizRequest;
import com.math.mathcha.dto.request.QuestionDTO;
import com.math.mathcha.dto.response.ResQuizResultDTO;
import com.math.mathcha.entity.Quiz;
import com.math.mathcha.service.quizService.QuizService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
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
    @PreAuthorize("hasRole('CONTENT_MANAGER')")
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
}

