package com.math.mathcha.service.quizService;

import com.math.mathcha.dto.request.LessonDTO;
import com.math.mathcha.dto.request.QuizDTO;

import java.util.List;

public interface QuizService {
    QuizDTO createQuiz(QuizDTO quizDTO, Integer quiz_id);

    QuizDTO getQuizById(Integer quiz_id);

    List<QuizDTO> getQuizByTopicId(int quiz_id);

    QuizDTO updateQuiz(QuizDTO quizDTO, Integer quiz_id);

    void deleteQuiz(Integer quiz_id);

}
