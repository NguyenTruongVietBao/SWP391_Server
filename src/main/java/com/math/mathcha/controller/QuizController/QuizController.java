package com.math.mathcha.controller.QuizController;

import com.math.mathcha.dto.request.QuestionDTO;
import com.math.mathcha.dto.request.QuizDTO;
import com.math.mathcha.dto.response.QuizResultDTO;
import com.math.mathcha.entity.Question.Question;
import com.math.mathcha.entity.Question.QuestionOption;
import com.math.mathcha.service.quizService.QuizService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/quiz")
public class QuizController {

    private QuizService quizService;

    @GetMapping("/question")
    public ResponseEntity<List<QuestionDTO>> getQuizQuestions() {
        List<QuestionDTO> questions = quizService.getQuizQuestions();
        return ResponseEntity.ok(questions);
    }

    @GetMapping("/chapter/{chapterId}")
    public ResponseEntity<List<QuestionDTO>> getQuizForChapter(@PathVariable int chapterId, @RequestParam int questionsPerTopic) {
        List<QuestionDTO> quiz = quizService.generateQuizForChapter(chapterId, questionsPerTopic);
        return ResponseEntity.ok(quiz);
    }

    @PostMapping("/{quizId}/evaluate")
    public ResponseEntity<QuizResultDTO> evaluateQuiz(@PathVariable Long quizId, @RequestBody EvaluateQuizRequest request) {
        QuizResultDTO result = quizService.evaluateQuiz(quizId, request.getUserId(), request.getQuizDTO());
        return ResponseEntity.ok(result);
    }
    @Getter
    @Setter
    public static class EvaluateQuizRequest {
        private Long userId;
        private QuizDTO quizDTO;

        // Getters and Setters
    }
}
