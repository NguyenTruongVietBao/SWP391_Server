package com.math.mathcha.mapper;

import com.math.mathcha.dto.request.ChapterDTO;
import com.math.mathcha.dto.request.QuizDTO;
import com.math.mathcha.entity.Chapter;
import com.math.mathcha.entity.Quiz;

public class QuizMapper {
    public static QuizDTO mapToQuizDTO(Quiz quiz){
        QuizDTO quizDTO = new QuizDTO();
        quizDTO.setQuiz_id(quiz.getQuiz_id());
        quizDTO.setContent(quiz.getContent());
        quizDTO.setTitle(quiz.getTitle());
        quizDTO.setOption(quiz.getOptions());
        quizDTO.setCorrectAnswer(quiz.getCorrectAnswer());
        return quizDTO;
    }

    public static Quiz mapToQuiz(QuizDTO quizDTO){
        Quiz quiz = new Quiz();
        quiz.setQuiz_id(quizDTO.getQuiz_id());
        quiz.setTitle(quizDTO.getTitle());
        quiz.setContent(quizDTO.getContent());
        quiz.setOptions(quizDTO.getOption());
        quiz.setCorrectAnswer(quizDTO.getCorrectAnswer());
        return quiz;
    }
}
