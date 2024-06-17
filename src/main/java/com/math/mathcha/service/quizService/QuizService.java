package com.math.mathcha.service.quizService;

import com.math.mathcha.dto.request.QuestionDTO;
import com.math.mathcha.dto.request.QuizDTO;
import com.math.mathcha.dto.response.QuizResultDTO;
import com.math.mathcha.entity.Question.Question;

import java.util.List;

public interface QuizService {
    public List<QuestionDTO> getQuizQuestions();

    public  List<Question> generateQuizForChapter(int chapterId, int questionPerTopic);

    public QuizResultDTO evaluateQuiz(QuizDTO quizDTO);


}
