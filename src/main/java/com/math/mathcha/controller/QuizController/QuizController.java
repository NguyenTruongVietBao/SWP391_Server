package com.math.mathcha.controller.QuizController;

import com.math.mathcha.dto.request.EvaluateQuizRequest;
import com.math.mathcha.dto.request.GenerateQuizRequest;
import com.math.mathcha.dto.request.QuestionDTO;
import com.math.mathcha.dto.request.QuizDTO;
import com.math.mathcha.dto.response.QuizResultDTO;
import com.math.mathcha.entity.Quiz;
import com.math.mathcha.service.quizService.QuizService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/quiz")
public class QuizController {

    private QuizService quizService;

    @GetMapping("/questions")
    public List<QuestionDTO> getQuizQuestions() {
        return quizService.getQuizQuestions();
    }



    @PostMapping("/topic/{topicId}/generate")
    public Quiz generateQuizForTopic(@PathVariable int topicId,
                                     @RequestBody GenerateQuizRequest request) {
        return quizService.generateQuizForTopic(topicId, request.getNumberOfQuestions(), request.getTimeLimit());
    }


    @PostMapping("/chapter/{chapterId}/generate")
    public Quiz generateQuizForChapter(@PathVariable int chapterId,
                                       @RequestBody GenerateQuizRequest request) {
        return quizService.generateQuizForChapter(chapterId, request.getNumberOfQuestions(), request.getTimeLimit());
    }

    @PostMapping("/evaluate/{quizId}/user/{userId}")
    public QuizResultDTO evaluateQuiz(@PathVariable Long quizId,
                                      @PathVariable Long userId,
                                      @RequestBody EvaluateQuizRequest request) {
        return quizService.evaluateQuiz(quizId, userId, request.getQuizDTO());
    }
}

