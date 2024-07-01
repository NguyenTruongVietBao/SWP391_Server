package com.math.mathcha.service.quizService;

import com.math.mathcha.dto.request.QuestionDTO;
import com.math.mathcha.dto.request.QuizDTO;
import com.math.mathcha.dto.response.QuizResultDTO;
import com.math.mathcha.entity.Question.Question;
import com.math.mathcha.entity.Quiz;
import com.math.mathcha.entity.QuizResult;

import java.util.List;

public interface QuizService {
    Quiz createQuiz(QuizDTO quizDTO);
    QuizResult saveQuizResult(Long quizId, QuizResultDTO quizResultDTO);
    QuizDTO generateQuizForTopic(int topicId, int numOfQuestions, int timeLimit);
    Quiz generateQuizForChapter(int chapterId, int numOfQuestions, int timeLimit);
    Quiz generateQuizForCourse(int courseId, int numOfQuestions, int timeLimit);




//    public List<QuestionDTO> getQuizQuestions();
//
//    public Quiz generateQuizForTopic(int topicId, int numberOfQuestions, int timeLimit);
//
////    public  List<QuestionDTO> generateQuizForChapter(int chapterId, int questionPerTopic);
//
//    public Quiz generateQuizForChapter(int chapterId, int questionPerTopic, int timeLimit);
//
//    public QuizResultDTO evaluateQuiz(Long quizId, Long userId, QuizDTO quizDTO);

}
