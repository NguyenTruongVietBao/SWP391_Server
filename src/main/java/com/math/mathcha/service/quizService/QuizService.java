package com.math.mathcha.service.quizService;

import com.math.mathcha.dto.request.QuestionDTO;
import com.math.mathcha.dto.request.QuizDTO;
import com.math.mathcha.dto.request.QuizResultDTO;
import com.math.mathcha.dto.response.ResQuizResultDTO;
import com.math.mathcha.entity.Quiz;

import java.util.List;

public interface QuizService {
    public List<QuestionDTO> getQuizQuestions();

    public Quiz generateQuizForTopic(int topicId, int numberOfQuestions, int timeLimit);

//    public  List<QuestionDTO> generateQuizForChapter(int chapterId, int questionPerTopic);
public Quiz generateQuizForCourse(int courseId, int numberOfQuestion, int timeLimit);

    public Quiz generateQuizForChapter(int chapterId,  int numberOfQuestions, int timeLimit);

    public ResQuizResultDTO evaluateQuiz( int enrollment_id, QuizDTO quizDTO);

    public List<QuizResultDTO> getQuizResultByEnrollmentId(int enrollment_id);

    public QuizResultDTO saveQuiz(int enrollment_id, int score, String quiz_name);
}
