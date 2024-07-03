package com.math.mathcha.service.quizService;

import com.math.mathcha.dto.request.QuestionDTO;
import com.math.mathcha.dto.request.QuizDTO;
import com.math.mathcha.dto.response.ResQuizResultDTO;
import com.math.mathcha.entity.Quiz;

import java.util.List;

public interface QuizService {
    public List<QuestionDTO> getQuizQuestions();

    public Quiz generateQuizForTopic(int topicId, int numberOfQuestions, int timeLimit);

//    public  List<QuestionDTO> generateQuizForChapter(int chapterId, int questionPerTopic);

    public Quiz generateQuizForChapter(int chapterId, int questionPerTopic, int timeLimit);

    public ResQuizResultDTO evaluateQuiz( int enrollment_id, QuizDTO quizDTO);

}
