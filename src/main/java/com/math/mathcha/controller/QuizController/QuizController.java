package com.math.mathcha.controller.QuizController;

import com.math.mathcha.dto.request.QuestionDTO;
import com.math.mathcha.dto.request.QuizDTO;
import com.math.mathcha.dto.response.QuizResultDTO;
import com.math.mathcha.entity.Question.QuestionOption;
import com.math.mathcha.service.quizService.QuizService;
import lombok.AllArgsConstructor;
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

    @PostMapping("/evaluate")
    public ResponseEntity<QuizResultDTO> evaluateQuiz(@RequestBody QuizDTO quizDTO) {
        QuizResultDTO result = quizService.evaluateQuiz(quizDTO);
        return ResponseEntity.ok(result);
    }
}
